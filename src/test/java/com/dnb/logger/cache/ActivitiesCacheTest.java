package com.dnb.logger.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ActivitiesCacheTest {

    @Test
    void givenExistentCode_whenGetDescription_thenReturnDescription() {
        Assertions.assertEquals("Viewed", ActivitiesCache.getDescription(1));
    }

    @Test
    void givenNonExistentCode_whenGetDescription_thenReturnNull() {
        Assertions.assertNull(ActivitiesCache.getDescription(100));
    }
}