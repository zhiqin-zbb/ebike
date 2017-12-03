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
 * 日期解析yyyy-MM-dd HH:mm:ss
 */
public class JackJsonDateTimeParse extends JsonDeserializer<Date> {
    private static final Logger LOG = LoggerFactory.getLogger(JackJsonDateTimeParse.class);

    @SuppressWarnings("deprecation")
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = jp.getText();
        if (date == null || date.trim().length() == 0) {
            return null;
        }
        if ("000-00-00".equals(date.trim())) {
            return new Date("0000-00-00");
        }
        if (19 == date.trim().length()) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        if (16 == date.trim().length()) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
        if (10 == date.trim().length() && date.trim().contains("-")) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        }
        if (5 == date.trim().length() && date.trim().contains(":")) {
            format = new SimpleDateFormat("HH:mm");
        }
        try {
            return format.parse(date);
        } catch (Exception e) {
            LOG.error("date parse Error.", e);
        }
        return null;
    }
}
