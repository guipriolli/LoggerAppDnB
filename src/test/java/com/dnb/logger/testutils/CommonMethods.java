package com.dnb.logger.testutils;

import com.dnb.logger.service.JsonReader;
import com.dnb.logger.service.XmlReader;
import com.dnb.logger.model.Log;
import com.dnb.logger.utils.DateUtils;
import org.junit.jupiter.api.Assertions;

import java.io.File;

public class CommonMethods {

    public static JsonReader getJsonFile(String fileName) {
        File file = new File(fileName);
        return new JsonReader(file);
    }

    public static XmlReader getXmlFile(String fileName) {
        File file = new File(fileName);
        return new XmlReader(file);
    }

    public static void assertLogFieldByField(Log log) {
        Log mockLog = buildMockLog();
        Assertions.assertEquals(mockLog.getUser(), log.getUser());
        Assertions.assertEquals(mockLog.getWebsite(), log.getWebsite());
        Assertions.assertEquals(mockLog.getActivityTypeDescription(), log.getActivityTypeDescription());
        Assertions.assertEquals(mockLog.getSignedInTime(), log.getSignedInTime());
    }

    public static Log buildMockLog() {
        return new Log.LogBuilder()
                .user("Sam")
                .website("abc.com")
                .activityTypeDescription("Viewed")
                .signedInTime(DateUtils.parseDate("13/01/2020", "dd/MM/yyyy"))
                .build();
    }
}
