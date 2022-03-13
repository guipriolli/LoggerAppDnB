package com.dnb.logger.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DateUtilsTest {

    @Test
    void givenDateString_whenParseDate_thenReturnDate() {
        Date date = new Date();
        String dateStr = date.toString();
        Date parsedDate = DateUtils.parseDate(dateStr, "EEE MMM dd HH:mm:ss zzz yyyy");
        assertEquals(dateStr, parsedDate.toString());
    }

    @Test
    void givenNonDateString_whenParseDate_thenReturnNull() {
        Date date = DateUtils.parseDate("any string", "EEE MMM dd HH:mm:ss zzz yyyy");
        assertNull(date);
    }

}