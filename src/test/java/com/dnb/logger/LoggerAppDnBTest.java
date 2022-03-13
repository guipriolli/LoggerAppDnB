package com.dnb.logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoggerAppDnBTest {

    @Test
    void givenNoArgs_whenRunningMain_thenThrowException() {
        String[] args = null;
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> LoggerAppDnB.main(null));
        Assertions.assertEquals("Please provide input and output paths.", exception.getMessage());
    }

    @Test
    void givenOneArg_whenRunningMain_thenThrowException() {
        String[] args = {"folder"};
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> LoggerAppDnB.main(args));
        Assertions.assertEquals("Please provide input and output paths.", exception.getMessage());
    }

    @Test
    void givenArgs_whenRunningMain_thenDoesNotThrowException() {
        String[] args = {"input", "output"};
        Assertions.assertDoesNotThrow(() -> LoggerAppDnB.main(args));
    }
}