package com.dnb.logger.service;

import com.dnb.logger.model.Log;
import com.dnb.logger.testutils.CommonMethods;
import com.dnb.logger.testutils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class XmlReaderTest {

    @Test
    void givenValidXmlFile_whenIsValid_thenReturnTrue() {
        XmlReader XmlReader = CommonMethods.getXmlFile(Constants.VALID_LOG_XML);
        Assertions.assertTrue(XmlReader.isValid());
    }

    @Test
    void givenInvalidSchemaXmlFile_whenIsValid_thenReturnFalse() {
        XmlReader XmlReader = CommonMethods.getXmlFile(Constants.INVALID_LOG_SCHEMA_XML);
        Assertions.assertFalse(XmlReader.isValid());
    }

    @Test
    void givenInvalidXmlFile_whenIsValid_thenReturnFalse() {
        XmlReader XmlReader = CommonMethods.getXmlFile(Constants.INVALID_LOG_XML);
        Assertions.assertFalse(XmlReader.isValid());
    }

    @Test
    void givenValidXmlFile_whenExtract_thenDoesNotThrowError() {
        Assertions.assertDoesNotThrow(() -> {
            XmlReader XmlReader = CommonMethods.getXmlFile(Constants.VALID_LOG_XML);
            XmlReader.isValid();
            XmlReader.extract();
        });
    }

    @Test
    void givenValidXmlFile_whenTransform_thenReturnLog() {
        XmlReader XmlReader = CommonMethods.getXmlFile(Constants.VALID_LOG_XML);
        XmlReader.isValid();
        XmlReader.extract();
        Log log = XmlReader.transform();
        CommonMethods.assertLogFieldByField(log);
    }
}