package com.dnb.logger.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class XmlSchemaCacheTest {

    @Test
    void whenGetSchema_thenReturnDescription() {
        Assertions.assertNotNull(XmlSchemaCache.getSchema());
    }

}