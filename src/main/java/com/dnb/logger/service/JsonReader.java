package com.dnb.logger.service;

import com.dnb.logger.model.Log;
import com.dnb.logger.utils.DateUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonReader extends LogReader {

    private static final Logger logger = LogManager.getLogger(JsonReader.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private JsonNode jsonNode = null;

    public JsonReader(File file) {
        super(file);
    }

    @Override
    protected boolean isValid() {
        try {
            jsonNode = mapper.readTree(file);
            return true;
        } catch (IOException e) {
            logger.error(file.getName() + " not valid.");
        }
        return false;
    }

    @Override
    protected void extract() {
        jsonNode = jsonNode.get("activity");

        if (jsonNode == null)
            throw new RuntimeException("Invalid json schema.");
    }

    @Override
    protected Log transform() {
        return new Log.LogBuilder()
                .user(jsonNode.get("userName").asText())
                .website(jsonNode.get("websiteName").asText())
                .activityTypeDescription(jsonNode.get("activityTypeDescription").asText())
                .signedInTime(DateUtils.parseDate(jsonNode.get("signedInTime").asText(), "MM/dd/yyyy"))
                .build();
    }

}
