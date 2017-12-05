package com.aaebike.common.date.format;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 日期解析yyyy-MM-dd
 */
public class JackJsonDateFormat extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String formattedDate = "";
        if (value != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = formatter.format(value);
        }
        jgen.writeString(formattedDate);
    }
}