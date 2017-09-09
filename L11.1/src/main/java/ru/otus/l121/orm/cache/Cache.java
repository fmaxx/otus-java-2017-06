package ru.otus.l121.orm.cache;

public interface Cache<K, V> {
    void put(CacheElement<K, V> element);

    CacheElement<K, V> get(K key);

    int getHintCount();

    int getMissCount();

    void dispose();

}
