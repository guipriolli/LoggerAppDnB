package com.dnb.logger.utils;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class FileResourcesUtils {

    public static File getFileFromResource(String fileName) throws URISyntaxException {
        URL resource = FileResourcesUtils.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException(fileName + " not found ");
        } else {
            return new File(resource.toURI());
        }
    }

    public static InputStream getFileFromResourceAsStream(String fileName) {
        InputStream inputStream = FileResourcesUtils.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException(fileName + " not found ");
        } else {
            return inputStream;
        }
    }
}
