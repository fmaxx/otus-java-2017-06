package ru.otus.l11.orm.cache;

public interface Cache<K, V> {
    void put(CacheElement<K, V> element);

    CacheElement<K, V> get(K key);

    int getHintCount();

    int getMissCount();

    void dispose();

}
