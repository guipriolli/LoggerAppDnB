package com.dnb.logger.cache;

import com.dnb.logger.utils.FileResourcesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ActivitiesCache {

    private static final Logger logger = LogManager.getLogger(ActivitiesCache.class);
    private static final String CSV = "activity.csv";
    private static final String COMMA_DELIMITER = ",";
    private static final Map<Integer, String> activities = new HashMap<>();

    static {
        initActivitiesMap();
    }

    private static void initActivitiesMap() {
        try {
            BufferedReader br = instantiateBufferReader();
            iterateLinesAndInitializeActivities(br);
        } catch (IOException | NumberFormatException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error upon reading activity.csv.");
        }
    }

    private static BufferedReader instantiateBufferReader() {
        InputStream inputStream = FileResourcesUtils.getFileFromResourceAsStream(CSV);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

    private static void iterateLinesAndInitializeActivities(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(COMMA_DELIMITER);
            Integer code = Integer.parseInt(values[0].replaceAll("^0+(?!$)", ""));
            String description = values[1];
            activities.put(code, description);
        }
    }

    public static String getDescription(Integer code) {
        if (activities.containsKey(code)) {
            return activities.get(code);
        }
        return null;
    }

}
