package com.company;

import org.xml.sax.SAXException;
import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

/*класс для проверки валидности XML документа с XSD схемой*/
public class ValidatorDOM {
    public void validatorDOM() throws SAXException {

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = "tariff.xml";
        String schemaName = "tariff_xsdShema.xsd";
        Schema schema = null;
        SchemaFactory factory = SchemaFactory.newInstance(language);


        schema = factory.newSchema(new File(schemaName));
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setSchema(schema);
        System.out.println(fileName + " is valid");

    }
}
