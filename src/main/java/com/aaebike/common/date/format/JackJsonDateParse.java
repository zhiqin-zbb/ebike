package com.aaebike.common.date.format;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期转换yyyy-MM-dd
 */
public class JackJsonDateParse extends JsonDeserializer<Date> {
    private static final Logger logger = LoggerFactory.getLogger(JackJsonDateParse.class);

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = jp.getText();
        if (date == null || date.startsWith("0000") || date.trim().length() == 0) {
            return null;
        }
        try {
            return format.parse(date);
        } catch (Exception e) {
            logger.error("date parse Error.", e);
        }
        return null;
    }
}