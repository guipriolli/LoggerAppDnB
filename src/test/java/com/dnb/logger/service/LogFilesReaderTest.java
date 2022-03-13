package com.dnb.logger.service;

import com.dnb.logger.model.Log;
import com.dnb.logger.testutils.CommonMethods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class LogFilesReaderTest {

    private static final String FOLDER = "src/test/resources";
    private static final String EMPTY_FOLDER = "src/test/resources/empty-folder";

    @Test
    void givenFolderPath_whenReadLogFiles_thenReturnLogs() {
        LogFilesReader logFilesReader = new LogFilesReader(FOLDER);
        List<Log> logs = logFilesReader.readLogFiles();
        Assertions.assertEquals(2, logs.size());
        for (Log log : logs) {
            CommonMethods.assertLogFieldByField(log);
        }
    }

    @Test
    void givenEmptyFolderPath_whenReadLogFiles_thenReturnEmptyList() {
        LogFilesReader logFilesReader = new LogFilesReader(EMPTY_FOLDER);
        List<Log> logs = logFilesReader.readLogFiles();
        Assertions.assertEquals(0, logs.size());
    }
}