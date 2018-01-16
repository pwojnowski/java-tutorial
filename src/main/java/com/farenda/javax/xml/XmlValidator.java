package com.farenda.javax.xml;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.nio.file.Files;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

public class XmlValidator {

    private static final String JAXP_SCHEMA_LANGUAGE
            = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory builderFactory = new DocumentBuilderFactoryImpl();
        builderFactory.setIgnoringComments(true);
        builderFactory.setNamespaceAware(true);
        builderFactory.setValidating(true);
        builderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA_NS_URI);
        //TODO what is it for: builderFactory.setSchema();

        DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
        docBuilder.setErrorHandler(new ValidationErrorsCollector());

        Document validDoc = docBuilder.parse(XmlValidator.class.getResourceAsStream("/xml/library.xml"));
        System.out.println("Document validated!");
    }

    private static class ValidationErrorsCollector implements ErrorHandler {

        @Override
        public void warning(SAXParseException exception) {
            System.out.println(exception.toString());
        }

        @Override
        public void error(SAXParseException exception) {
            System.out.println(exception.toString());
        }

        @Override
        public void fatalError(SAXParseException exception) {
            System.out.println(exception.toString());
        }
    }
}