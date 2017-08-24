package ru.otus.l91.orm.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TResultHandler <T> {
    T handle(ResultSet resultSet) throws SQLException;
}
