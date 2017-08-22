package ru.otus.l81;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JsonHelper {

    public static String toJSONString(Object object) {
        return convertToJsonObject(object).build().toString();
    }

    private static JsonObjectBuilder convertToJsonObject(Object object) {

        JsonObjectBuilder builder = Json.createObjectBuilder();
        List<Field> fields = getFields(object.getClass());

        for (Field field : fields) {

            // VALUE
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            // TYPE
            Class type = field.getType();

            if (value == null) {
                builder.addNull(field.getName());
            } else if (type.isPrimitive()) {
                // PRIMITIVES
                if (value instanceof Byte) {
                    builder.add(field.getName(), (byte) value);
                } else if (value instanceof Integer) {
                    builder.add(field.getName(), (int) value);
                } else if (value instanceof Short) {
                    builder.add(field.getName(), (short) value);
                } else if (value instanceof Long) {
                    builder.add(field.getName(), (long) value);
                } else if (value instanceof Float) {
                    builder.add(field.getName(), (float) value);
                } else if (value instanceof Double) {
                    builder.add(field.getName(), (double) value);
                } else if (value instanceof Boolean) {
                    builder.add(field.getName(), (boolean) value);
                } else if (value instanceof Character) {
                    builder.add(field.getName(), (char) value);
                }
            } else if (value instanceof String) {
                // STRINGS
                builder.add(field.getName(), (String) value);
            } else if (type.isArray()) {
                JsonArrayBuilder arrayBuilder = convertArray((Object[]) value);
                builder.add(field.getName(), arrayBuilder);
            } else if (Collection.class.isAssignableFrom(type)) {
                // COLLECTIONS
                value = ((Collection) value).toArray();
                JsonArrayBuilder arrayBuilder = convertArray((Object[]) value);
                builder.add(field.getName(), arrayBuilder);
            } else if (value instanceof Object) {
                // OBJECTS
                builder.add(field.getName(), convertToJsonObject(value));
            }
        }
        return builder;
    }

    static JsonArrayBuilder convertArray(Object[] array) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object o : array) {
            arrayBuilder.add(convertToJsonObject(o));
        }
        return arrayBuilder;
    }

    static List<Field> getFields(Class<?> type) {
        return new ArrayList<>(Arrays.asList(type.getDeclaredFields()));
    }

}
