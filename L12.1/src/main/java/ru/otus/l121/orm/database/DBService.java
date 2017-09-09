package ru.otus.l121.orm.database;


import ru.otus.l121.orm.cache.Cache;
import ru.otus.l121.orm.data.UserDataSet;

import java.util.List;

public interface DBService {
    String getLocalStatus();

    long save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet read(String name, String pass);

    List<UserDataSet> readAll();

    void shutdown();

    Cache<Long, UserDataSet> getUserCache();
}
