package ru.otus.l41;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.*;

/**
 * Created by maximfirsov on 05/06/2017.
 *
 * Memory: -Xms64m -Xmx64m -XX:MaxMetaspaceSize=32m
 *
 * результаты для разных GC

    1) -XX:+UseParallelGC
    PS MarkSweep,           runs: 52,   durations: 12732
    PS Scavenge,            runs: 4,    durations: 120

    2) -XX:+UseConcMarkSweepGC -XX:+UseParNewGC
    ConcurrentMarkSweep,    runs: 19,   duration: 37333
    ParNew,                 runs: 6,    duration: 300

    3) -XX:+UseSerialGC
    Copy,                   runs: 5,    duration: 126
    MarkSweepCompact,       runs: 12,   duration: 1642

    4) -XX:+UseG1GC
    G1 Young Generation,    runs: 28,   duration: 822
    G1 Old Generation,      runs: 5,    duration: 1336
 *
 *
 */
public class Main {

    static HashMap<String, ArrayList<Long>> stats = new HashMap<>();
    static long startTime;

    public static void main(String[] args) throws IOException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        setupGC();

        int size = 500 * 1000;
        MemoryLeakStand stand = new MemoryLeakStand();
        stand.run(size);
    }

    private static void setupGC() {
        startTime = System.currentTimeMillis();
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            System.out.println(gcbean.getName());

            stats.put(gcbean.getName(), new ArrayList<>());

            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    long duration = info.getGcInfo().getDuration();
                    stats.get(info.getGcName()).add(duration);
                    printStats();
                }
            };

            emitter.addNotificationListener(listener, null, null);
        }
    }

    private static void printStats() {
        long appTime = System.currentTimeMillis() - startTime;
        for (String name : stats.keySet()){
            ArrayList<Long> list = stats.get(name);
            long totalTimeMS = list.stream().mapToLong(Long::longValue).sum();
            System.out.println("GC: " + name + ", runs: " + list.size() + ", duration: " + totalTimeMS + "/" + appTime);
        }
    }
}
