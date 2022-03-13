package com.dnb.logger;

import com.dnb.logger.model.Log;
import com.dnb.logger.service.LogFilesReader;
import com.dnb.logger.service.LogWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LoggerAppDnB {

    private static final Logger logger = LogManager.getLogger(LoggerAppDnB.class);

    public static void main(String[] args) {
        checkParameters(args);
        List<Log> logs = readLogs(args[0]);
        writeLogs(args[1], logs);
    }

    private static void checkParameters(String[] args) {
        if (args == null || args.length < 2) {
            throw new RuntimeException("Please provide input and output paths.");
        }
    }

    private static List<Log> readLogs(String path) {
        try {
            LogFilesReader logFilesReader = new LogFilesReader(path);
            return logFilesReader.readLogFiles();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("Error when reading logs input.");
        }
    }

    private static void writeLogs(String path, List<Log> logs) {
        LogWriter logWriter = new LogWriter(path, logs);
        logWriter.writeLog();
    }
}
