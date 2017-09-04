package ru.otus.l11.orm.cache;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

public class CacheImpl<K, V> implements Cache<K, V> {

    private static final int TIME_THRESHOLD_MS = 5;

    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;

    private final Map<K, SoftReference<CacheElement<K, V>>> elements = new LinkedHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public CacheImpl(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
        this.isEternal = lifeTimeMs == 0 && idleTimeMs == 0 || isEternal;
    }

    @Override
    public void put(CacheElement<K, V> element) {
        if (elements.size() == maxElements) {
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
        }

        K key = element.getKey();
        SoftReference softReference = new SoftReference(element);
        elements.put(key, softReference);

        if (!isEternal) {
            if (lifeTimeMs != 0) {
                TimerTask lifeTimerTask = getTimerTask(key, lifeElement -> lifeElement.getCreationTime() + lifeTimeMs);
                timer.schedule(lifeTimerTask, lifeTimeMs);
            }

            if (idleTimeMs != 0) {
                TimerTask idleTimerTask = getTimerTask(key, lifeElement -> lifeElement.getLastAccessTime() + lifeTimeMs);
                timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
            }
        }
    }

    public CacheElement<K, V> get(K key) {
        SoftReference softReference = elements.get(key);
        CacheElement<K, V> element = (CacheElement<K, V>) softReference.get();

        if (element != null) {
            hit++;
            element.setAccessed();
        } else {
            miss++;
        }
        return element;
    }

    @Override
    public int getHintCount() {
        return hit;
    }

    @Override
    public int getMissCount() {
        return miss;
    }

    @Override
    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<CacheElement<K, V>, Long> timerFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                SoftReference softReference = elements.get(key);
                if(softReference == null){
                    return;
                }
                CacheElement<K, V> element = (CacheElement<K, V>) softReference.get();
                if (element == null ||
                        isTimeLassThan(
                                timerFunction.apply(element),
                                System.currentTimeMillis()
                        )) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }

    private boolean isTimeLassThan(long time, long position) {
        return time < position + TIME_THRESHOLD_MS;
    }
}
