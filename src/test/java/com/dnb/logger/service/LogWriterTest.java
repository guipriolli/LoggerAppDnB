package com.dnb.logger.service;

import com.dnb.logger.model.Log;
import com.dnb.logger.testutils.CommonMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

class LogWriterTest {

    private static final String OUTPUT_FOLDER = "src/test/resources/output";
    private static final String NONEXISTENT_FOLDER = "src/test/resources/folder";
    private static final String OUTPUT_FILE = "src/test/resources/output/output-logs.txt";

    @AfterEach
    void setUp() throws IOException {
        File file = new File(OUTPUT_FILE);
        Files.deleteIfExists(file.toPath());
    }

    @Test
    void givenFolderPathAndListOfLogs_whenWriteLog_thenSaveFile() {
        List<Log> logs = Collections.singletonList(CommonMethods.buildMockLog());
        LogWriter logWriter = new LogWriter(OUTPUT_FOLDER, logs);
        Assertions.assertDoesNotThrow(logWriter::writeLog);
        File file = new File(OUTPUT_FILE);
        Assertions.assertTrue(file.isFile());
    }

    @Test
    void givenNonExistentFolderPathAndListOfLogs_whenWriteLog_thenSaveFile() {
        List<Log> logs = Collections.singletonList(CommonMethods.buildMockLog());
        LogWriter logWriter = new LogWriter(NONEXISTENT_FOLDER, logs);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, logWriter::writeLog);
        Assertions.assertEquals(NONEXISTENT_FOLDER + "not found.", exception.getMessage());
    }
}