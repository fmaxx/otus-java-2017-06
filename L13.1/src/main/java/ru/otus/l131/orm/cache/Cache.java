package ru.otus.l131.orm.cache;

import java.util.List;

public interface Cache<K, V> {
    void put(CacheElement<K, V> element);

    CacheElement<K, V> get(K key);

    List<CacheElement<K, V>> allElements();
    int getHintCount();

    int getMissCount();

    void dispose();

}
