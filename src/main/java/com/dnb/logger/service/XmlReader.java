package com.dnb.logger.service;

import com.dnb.logger.cache.ActivitiesCache;
import com.dnb.logger.cache.XmlSchemaCache;
import com.dnb.logger.model.Log;
import com.dnb.logger.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlReader extends LogReader {

    private static final Logger logger = LogManager.getLogger(XmlReader.class);
    private Document document = null;

    public XmlReader(File file) {
        super(file);
    }

    @Override
    protected boolean isValid() {
        try {
            Validator validator = XmlSchemaCache.getSchema().newValidator();
            validator.validate(new StreamSource(this.file));
            return true;
        } catch (IOException | SAXException e) {
            logger.error(file.getName() + " not valid.");
        }
        return false;
    }

    @Override
    protected void extract() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(this.file);
            document.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("Error upon extracting the log of " + file.getName());
        }
    }

    @Override
    protected Log transform() {
        return new Log.LogBuilder()
                .user(document.getElementsByTagName("userName").item(0).getTextContent())
                .website(document.getElementsByTagName("websiteName").item(0).getTextContent())
                .activityTypeDescription(getActivityDescription(document.getElementsByTagName("activityTypeCode").item(0).getTextContent()))
                .signedInTime(DateUtils.parseDate(document.getElementsByTagName("loggedInTime").item(0).getTextContent(), "yyyy-MM-dd"))
                .build();
    }

    private String getActivityDescription(String codeStr) {
        if (codeStr != null) {
            Integer code = Integer.parseInt(codeStr.replaceAll("^0+(?!$)", ""));
            return ActivitiesCache.getDescription(code);
        }
        return null;
    }

}
