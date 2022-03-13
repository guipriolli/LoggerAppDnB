package com.dnb.logger.service;

import com.dnb.logger.model.Log;
import com.dnb.logger.testutils.CommonMethods;
import com.dnb.logger.testutils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LogReaderTest {

    @Test
    void givenValidJsonFile_whenExtractLog_thenReturnLog() {
        LogReader reader = CommonMethods.getJsonFile(Constants.VALID_LOG_JSON);
        Log log = reader.extractLog();
        CommonMethods.assertLogFieldByField(log);
    }

    @Test
    void givenInvalidSchemaJsonFile_whenExtractLog_thenReturnNull() {
        LogReader reader = CommonMethods.getJsonFile(Constants.INVALID_LOG_SCHEMA_JSON);
        Log log = reader.extractLog();
        Assertions.assertNull(log);
    }

    @Test
    void givenInvalidJsonFile_whenExtractLog_thenReturnNull() {
        LogReader reader = CommonMethods.getJsonFile(Constants.INVALID_LOG_JSON);
        Log log = reader.extractLog();
        Assertions.assertNull(log);
    }

    @Test
    void givenValidXmlFile_whenExtractLog_thenReturnLog() {
        LogReader reader = CommonMethods.getXmlFile(Constants.VALID_LOG_XML);
        Log log = reader.extractLog();
        CommonMethods.assertLogFieldByField(log);
    }

    @Test
    void givenInvalidSchemaXmlFile_whenExtractLog_thenReturnNull() {
        LogReader reader = CommonMethods.getXmlFile(Constants.INVALID_LOG_SCHEMA_XML);
        Log log = reader.extractLog();
        Assertions.assertNull(log);
    }

    @Test
    void givenInvalidXmlFile_whenExtractLog_thenReturnNull() {
        LogReader reader = CommonMethods.getXmlFile(Constants.INVALID_LOG_XML);
        Log log = reader.extractLog();
        Assertions.assertNull(log);
    }
}