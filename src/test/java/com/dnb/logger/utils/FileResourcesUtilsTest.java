package com.dnb.logger.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FileResourcesUtilsTest {

    private static final String EXISTENT_FILE = "activity.csv";
    private static final String NON_EXISTENT_FILE = "non-existent-file.csv";

    @Test
    void givenExistentFile_whenGetFileFromResource_thenReturnFile() throws URISyntaxException {
        File file = FileResourcesUtils.getFileFromResource(EXISTENT_FILE);
        Assertions.assertNotNull(file);
    }

    @Test
    void givenNonexistentFile_whenGetFileFromResource_thenThrowError() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> FileResourcesUtils.getFileFromResource(NON_EXISTENT_FILE));
        Assertions.assertEquals(NON_EXISTENT_FILE + " not found ", exception.getMessage());
    }

    @Test
    void givenExistentFile_whenGetFileFromResourceAsStream_thenReturnInputStream() {
        InputStream inputStream = FileResourcesUtils.getFileFromResourceAsStream(EXISTENT_FILE);
        Assertions.assertNotNull(inputStream);
    }

    @Test
    void givenNonexistentFile_whenGetFileFromResourceAsStream_thenThrowError() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> FileResourcesUtils.getFileFromResourceAsStream(NON_EXISTENT_FILE));
        Assertions.assertEquals(NON_EXISTENT_FILE + " not found ", exception.getMessage());
    }
}