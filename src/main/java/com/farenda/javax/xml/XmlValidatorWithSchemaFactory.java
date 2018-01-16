package com.farenda.javax.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

public class XmlValidatorWithSchemaFactory {

    public static void main(String[] args) throws Exception {
        String schemaFilename = "xml/books.xsd";
        String xmlFilename = "xml/library-no-schema.xml";

        XmlValidatorWithSchemaFactory validator
                = new XmlValidatorWithSchemaFactory();
        Document document = validator.loadXml(xmlFilename);

        try {
            validator.validate(document, schemaFilename);
            System.out.println("The file is valid!");
        } catch (Exception e) {
            System.err.println("The file is invalid! Reason:");
            System.err.println(e.getMessage());
        }
    }

    public void validate(Document document, String schemaFile)
            throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(
                XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(
                new StreamSource(getInputStream(schemaFile)));

        Validator validator = schema.newValidator();
        validator.validate(new DOMSource(document));
    }

    private DocumentBuilder createDocumentBuilder()
            throws ParserConfigurationException {
        DocumentBuilderFactory builderFactory
                = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        return builderFactory.newDocumentBuilder();
    }

    private Document loadXml(String xmlToValidate) throws Exception {
        DocumentBuilder builder = createDocumentBuilder();
        return builder.parse(getInputStream(xmlToValidate));
    }

    private InputStream getInputStream(String filename) {
        return getClass().getClassLoader().getResourceAsStream(filename);
    }
}
