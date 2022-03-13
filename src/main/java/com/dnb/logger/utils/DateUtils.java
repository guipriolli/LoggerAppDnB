package com.dnb.logger.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final Logger logger = LogManager.getLogger(DateUtils.class);

    public static Date parseDate(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

}
