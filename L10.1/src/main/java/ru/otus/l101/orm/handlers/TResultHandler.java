package ru.otus.l101.orm.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TResultHandler <T> {
    T handle(ResultSet resultSet) throws SQLException;
}
