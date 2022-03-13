package com.dnb.logger.service;

import com.dnb.logger.model.Log;
import com.dnb.logger.testutils.CommonMethods;
import com.dnb.logger.testutils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonReaderTest {

    @Test
    void givenValidJsonFile_whenIsValid_thenReturnTrue() {
        JsonReader jsonReader = CommonMethods.getJsonFile(Constants.VALID_LOG_JSON);
        Assertions.assertTrue(jsonReader.isValid());
    }

    @Test
    void givenInvalidSchemaJsonFile_whenIsValid_thenReturnTrue() {
        JsonReader jsonReader = CommonMethods.getJsonFile(Constants.INVALID_LOG_SCHEMA_JSON);
        Assertions.assertTrue(jsonReader.isValid());
    }

    @Test
    void givenInvalidJsonFile_whenIsValid_thenReturnFalse() {
        JsonReader jsonReader = CommonMethods.getJsonFile(Constants.INVALID_LOG_JSON);
        Assertions.assertFalse(jsonReader.isValid());
    }

    @Test
    void givenValidJsonFile_whenExtract_thenDoesNotThrowError() {
        Assertions.assertDoesNotThrow(() -> {
            JsonReader jsonReader = CommonMethods.getJsonFile(Constants.VALID_LOG_JSON);
            jsonReader.isValid();
            jsonReader.extract();
        });
    }

    @Test
    void givenInvalidSchemaJsonFile_whenExtract_thenThrowError() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            JsonReader jsonReader = CommonMethods.getJsonFile(Constants.INVALID_LOG_SCHEMA_JSON);
            jsonReader.isValid();
            jsonReader.extract();
        });
    }

    @Test
    void givenValidJsonFile_whenTransform_thenReturnLog() {
        JsonReader jsonReader = CommonMethods.getJsonFile(Constants.VALID_LOG_JSON);
        jsonReader.isValid();
        jsonReader.extract();
        Log log = jsonReader.transform();
        CommonMethods.assertLogFieldByField(log);
    }

}