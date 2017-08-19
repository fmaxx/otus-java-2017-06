package ru.otus.l81;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JsonHelper {

    public static String toJSONString(Object object){
        return convertToJsonObject(object).build().toString();
    }

    private static JsonObjectBuilder convertToJsonObject(Object object) {

        JsonObjectBuilder builder = Json.createObjectBuilder();
        List<Field> fields = getFields(object.getClass());

        for (Field field : fields) {

            JsonValue.ValueType jsonValueType = getJsonValue(field, object);
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (fieldValue == null) {
                jsonValueType = JsonValue.ValueType.NULL;
            }else if(fieldValue instanceof Collection){
                jsonValueType = JsonValue.ValueType.ARRAY;
                fieldValue = ((Collection) fieldValue).toArray();
            }

            switch (jsonValueType) {
                case NUMBER:
                    if (fieldValue instanceof Byte) {
                        builder.add(field.getName(), (byte) fieldValue);
                    } else if (fieldValue instanceof Integer) {
                        builder.add(field.getName(), (int) fieldValue);
                    } else if (fieldValue instanceof Short) {
                        builder.add(field.getName(), (short) fieldValue);
                    } else if (fieldValue instanceof Long) {
                        builder.add(field.getName(), (long) fieldValue);
                    } else if (fieldValue instanceof Float) {
                        builder.add(field.getName(), (float) fieldValue);
                    } else if (fieldValue instanceof Double) {
                        builder.add(field.getName(), (double) fieldValue);
                    }
                    break;

                case OBJECT:
                    builder.add(field.getName(), convertToJsonObject(fieldValue));
                    break;
                case STRING:
                    if (fieldValue instanceof Character) {
                        builder.add(field.getName(), (char) fieldValue);
                    } else {
                        builder.add(field.getName(), (String) fieldValue);
                    }
                    break;

                case FALSE:
                case TRUE:
                    builder.add(field.getName(), (boolean) fieldValue);
                    break;

                case NULL:
                    builder.addNull(field.getName());
                    break;

                case ARRAY:
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    Object[] test = (Object[]) fieldValue;
                    for (Object o : test) {
                        arrayBuilder.add(convertToJsonObject(o));
                    }
                    builder.add(field.getName(), arrayBuilder);
                    break;
            }
        }
        return builder;
    }


    static Boolean isPrimitiveType(Field field, Object object) throws IllegalAccessException {
        Object fieldValue = field.get(object);
        return fieldValue.getClass().isPrimitive() ||
                fieldValue instanceof Byte ||
                fieldValue instanceof Short ||
                fieldValue instanceof Integer ||
                fieldValue instanceof Long ||
                fieldValue instanceof Float ||
                fieldValue instanceof Double ||
                fieldValue instanceof Character ||
                fieldValue instanceof Boolean;
    }

    static JsonValue.ValueType getJsonValue(Field field, Object object) {
        try {
            if (isArray(field, object)) {
                return JsonValue.ValueType.ARRAY;
            } else {
                Object fieldValue = field.get(object);
                if (isPrimitiveType(field, object)) {
                    if (fieldValue instanceof Boolean) {
                        return (Boolean) fieldValue ? JsonValue.ValueType.TRUE : JsonValue.ValueType.FALSE;
                    } else if (fieldValue instanceof Character) {
                        return JsonValue.ValueType.STRING;
                    }
                    return JsonValue.ValueType.NUMBER;
                } else {
                    if (fieldValue instanceof String) {
                        return JsonValue.ValueType.STRING;
                    } else {
                        return JsonValue.ValueType.OBJECT;
                    }

                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return JsonValue.ValueType.NULL;
    }

    static Boolean isArray(Field field, Object object) throws IllegalAccessException {
        Object fieldValue = field.get(object);
        return fieldValue.getClass().isArray();
    }

    static List<Field> getFields(Class<?> type) {
        return new ArrayList<>(Arrays.asList(type.getDeclaredFields()));
    }

}
