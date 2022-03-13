package com.dnb.logger.service;

import com.dnb.logger.model.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class LogWriter {

    private static final Logger logger = LogManager.getLogger(LogWriter.class);

    private final String path;
    private final List<Log> logs;

    public LogWriter(String path, List<Log> logs) {
        this.path = path;
        this.logs = logs;
    }

    public void writeLog() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(format);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/output-logs.txt"));
            for (Log log : logs) {
                writer.append(objectMapper.writeValueAsString(log)).append("\n");
            }
            writer.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(path + "not found.");
        }
    }

}
