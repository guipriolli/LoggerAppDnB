package com.dnb.logger.service;

import com.dnb.logger.model.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogFilesReader {

    private static final Logger logger = LogManager.getLogger(LogFilesReader.class);

    private final String path;

    public LogFilesReader(String path) {
        this.path = path;
    }

    public List<Log> readLogFiles() {
        File[] files = getFiles();

        List<Log> logs = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()) {
                Log log = processFile(file);
                if (log != null) {
                    logs.add(log);
                }
            } else {
                logger.error(file.getAbsolutePath() + " is not a file.");
            }
        }

        return logs;
    }

    private File[] getFiles() {
        File folder = new File(path);
        return folder.listFiles();
    }

    private Log processFile(File file) {
        switch (getFileExtension(file)) {
            case "json" -> {
                LogReader jsonReader = new JsonReader(file);
                return jsonReader.extractLog();
            }
            case "xml" -> {
                LogReader xmlReader = new XmlReader(file);
                return xmlReader.extractLog();
            }
            default -> {
                logger.warn("File format not accepted for " + file.getName());
                return null;
            }
        }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index + 1);
    }

}
