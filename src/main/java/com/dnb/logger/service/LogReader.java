package com.dnb.logger.service;

import com.dnb.logger.model.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public abstract class LogReader {

    private static final Logger logger = LogManager.getLogger(XmlReader.class);

    protected final File file;

    public LogReader(File file) {
        this.file = file;
    }

    public final Log extractLog() {
        try {
            if (isValid()) {
                extract();
                return transform();
            } else {
                logger.error(file.getAbsolutePath() + " is not valid.");
            }
        } catch (RuntimeException e) {
            logger.error("Error when extracting log.");
        }
        return null;
    }

    abstract boolean isValid();

    abstract void extract();

    abstract Log transform();

}
