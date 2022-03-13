package com.dnb.logger.cache;

import com.dnb.logger.utils.FileResourcesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.net.URISyntaxException;

public class XmlSchemaCache {

    private static final Logger logger = LogManager.getLogger(XmlSchemaCache.class);
    private static final String XSD = "schema.xsd";
    private static Schema schema = null;

    static {
        initSchema();
    }

    private static void initSchema() {
        try {
            File file = FileResourcesUtils.getFileFromResource(XSD);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = factory.newSchema(file);
        } catch (URISyntaxException | SAXException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error upon reading schema.xsd.");
        }
    }

    public static Schema getSchema() {
        return schema;
    }

}
