package ru.otus.l91.orm.executor;

import ru.otus.l91.orm.Config;
import ru.otus.l91.orm.data.DataSet;
import ru.otus.l91.orm.helpers.ReflectionHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public <T extends DataSet> boolean save(T user){

        List<Field> fields = getFieldsToWrite(user);

        if(fields == null || fields.size() == 0){
            return false;
        }

        String tableName = user.getClass().getAnnotation(Table.class).name();
        String query = "INSERT INTO " + tableName;
        List<String> params = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for(Field f : fields){
            Column column = f.getAnnotation(Column.class);
            String fieldName = column.name();
            Object fieldValue = null;
            names.add(fieldName);

            // try to get the value
            Boolean accessibility = false;
            try {
                fieldValue = f.get(user);
                accessibility = true;
            } catch (IllegalAccessException e) {
                accessibility = false;
                // open access
                f.setAccessible(true);
                // and try to read again
                try {
                    fieldValue = f.get(user);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
            // restore the access level
            f.setAccessible(accessibility);

            // wrapping out with quotes for the query
            params.add("'" + fieldValue.toString()+ "'");
        }

        // wrapping out for the query
        String namesString = "( " + String.join(", ", names) + " )";
        String paramsString = "( " + String.join(", ", params) + " )";

        query += " " + namesString + " VALUES " +  System.lineSeparator() + paramsString + ";";

//        System.out.println("query : " + query);

        try {
            return update(query) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private List<Field>  getFieldsToWrite(Object object){
        // is it an Entity ?
        Boolean isEntity = object.getClass().getAnnotation(Entity.class) != null;
        if(!isEntity) return null;

        // is it related with a table?
        Boolean isUserTable = object.getClass().getAnnotation(Table.class) != null;
        if(!isUserTable) return null;

        return ReflectionHelper.getFieldsByAnnotation(object.getClass(), Column.class);
    }

    public <T extends DataSet> T load(long id, Class<T> clazz){
        try(Statement statement = connection.createStatement()){
            String query = "SELECT * FROM " + Config.TABLE_NAME + " WHERE id=" + id + ";";
            ResultSet resultSet = null;
            try {
                statement.execute(query);
                resultSet = statement.getResultSet();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(resultSet == null){
                return null;
            }

            try {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                Object[] values = null;

                if (resultSet.next()) {
                    values = new Object[columnCount];
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                        values[i - 1] = resultSet.getObject(i);
                    }
                }

                // create a nre instance using values from the database
                if(values != null){
                    return ReflectionHelper.instantiate(clazz, values);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int update(String query) throws SQLException {
        try(Statement statement = connection.createStatement()){
            statement.execute(query);
            return  statement.getUpdateCount();
        }
    }
}
