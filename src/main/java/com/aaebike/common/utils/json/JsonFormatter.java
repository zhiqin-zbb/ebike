package com.aaebike.common.utils.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by zhangbinbin on 2017/9/18.
 */
public class JsonFormatter {
    private static final ThreadLocal<ObjectMapper> INCLUDE_NULL_MAPPER = new ThreadLocal();

    private static final ThreadLocal<ObjectMapper> NOT_INCLUDE_NULL_MAPPER = new ThreadLocal();

    public JsonFormatter() {
    }

    private static ObjectMapper getMapper(boolean serializeNull) {
        ThreadLocal<ObjectMapper> tl = serializeNull ? INCLUDE_NULL_MAPPER : NOT_INCLUDE_NULL_MAPPER;
        if (null == tl.get()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            if (!serializeNull) {
                mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
                mapper.disable(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES);
            }
            tl.set(mapper);
        }

        return tl.get();
    }

    public static String toJsonString(Object obj) throws IOException {
        return toJsonString(obj, true);
    }

    public static String toJsonAsString(Object obj) throws IOException {
        return toJsonAsString(obj, true);
    }

    public static byte[] toJsonAsBytes(Object obj) throws IOException {
        return toJsonAsBytes(obj, true);
    }

    public static void toJsonToFile(File file, Object obj) throws IOException {
        toJsonToFile(file, obj, true);
    }

    public static void toJsonToOutputStream(OutputStream out, Object obj) throws IOException {
        toJsonToOutputStream(out, obj, true);
    }

    public static void toJsonToWriter(Writer writer, Object obj) throws IOException {
        toJsonToWriter(writer, obj, true);
    }

    public static <T> T toObject(String json, Class<T> clazz) throws IOException {
        return toObject(json, clazz, true);
    }

    public static <T> T toObject(byte[] src, Class<T> clazz) throws IOException {
        return toObject(src, clazz, true);
    }

    public static <T> T toObject(File file, Class<T> clazz) throws IOException {
        return toObject(file, clazz, true);
    }

    public static <T> T toObject(InputStream input, Class<T> clazz) throws IOException {
        return toObject(input, clazz, true);
    }

    public static <T> T toObject(Reader reader, Class<T> clazz) throws IOException {
        return toObject(reader, clazz, true);
    }

    public static <T> T toObject(URL url, Class<T> clazz) throws IOException {
        return toObject(url, clazz, true);
    }

    public static String toJsonString(Object obj, boolean serializeNull) throws IOException {
        return getMapper(serializeNull).writeValueAsString(obj);
    }

    public static String toJsonAsString(Object obj, boolean serializeNull) throws IOException {
        return getMapper(serializeNull).writeValueAsString(obj);
    }

    public static byte[] toJsonAsBytes(Object obj, boolean serializeNull) throws IOException {
        return getMapper(serializeNull).writeValueAsBytes(obj);
    }

    public static void toJsonToFile(File file, Object obj, boolean serializeNull) throws IOException {
        getMapper(serializeNull).writeValue(file, obj);
    }

    public static void toJsonToOutputStream(OutputStream out, Object obj, boolean serializeNull) throws IOException {
        getMapper(serializeNull).writeValue(out, obj);
    }

    public static void toJsonToWriter(Writer writer, Object obj, boolean serializeNull) throws IOException {
        getMapper(serializeNull).writeValue(writer, obj);
    }

    public static <T> T toObject(String json, Class<T> clazz, boolean serializeNull) throws IOException {
        return getMapper(serializeNull).readValue(json, clazz);
    }

    public static <T> T toObject(byte[] src, Class<T> clazz, boolean serializeNull) throws IOException {
        return getMapper(serializeNull).readValue(src, clazz);
    }

    public static <T> T toObject(File file, Class<T> clazz, boolean serializeNull) throws IOException {
        return getMapper(serializeNull).readValue(file, clazz);
    }

    public static <T> T toObject(InputStream input, Class<T> clazz, boolean serializeNull) throws IOException {
        return getMapper(serializeNull).readValue(input, clazz);
    }

    public static <T> T toObject(Reader reader, Class<T> clazz, boolean serializeNull) throws IOException {
        return getMapper(serializeNull).readValue(reader, clazz);
    }

    public static <T> T toObject(URL url, Class<T> clazz, boolean serializeNull) throws IOException {
        return getMapper(serializeNull).readValue(url, clazz);
    }

    public static void setDateFormat(DateFormat dateFormat) {
        getMapper(true).setDateFormat(dateFormat);
        getMapper(false).setDateFormat(dateFormat);
    }
}
