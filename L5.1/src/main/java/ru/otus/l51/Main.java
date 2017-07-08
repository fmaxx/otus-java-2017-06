package ru.otus.l51;

import com.sun.management.GarbageCollectionNotificationInfo;
import ru.otus.l51.framework.Runner;
import ru.otus.l51.tests.EducationTests;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.*;

/**
 * Created by maximfirsov on 06/07/2017.
 *
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        System.out.println("--- run classes ---");
//        Runner.run(new Class[]{EducationTests.class});

        System.out.println("--- run package ---");
        Runner.run("ru.otus.l51.tests");

    }
}
