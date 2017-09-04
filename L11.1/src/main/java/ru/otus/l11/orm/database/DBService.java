package ru.otus.l11.orm.database;


import ru.otus.l11.orm.data.UserDataSet;

import java.util.List;

public interface DBService {
    String getLocalStatus();

    long save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

    void shutdown();
}
