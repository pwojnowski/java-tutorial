package com.farenda.javax.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXMLUsingDOM {

    public static void main(String[] args) throws Exception {
        Document library = createDocument();
        Element books = addRootElement(library);
        books.appendChild(createBook(library));

        writeToFile(library);
    }

    private static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.newDocument();
    }

    private static Element addRootElement(Document library) {
        Element books = library.createElement("books");
        books.setAttribute("library", "Kraków");
        library.appendChild(books);
        return books;
    }

    private static Element createBook(Document doc) {
        Element book = doc.createElement("book");
        book.appendChild(createTitleNode(doc));
        return book;
    }

    private static Element createTitleNode(Document doc) {
        Element titleNode = doc.createElement("title");
        titleNode.appendChild(doc.createComment("Great book!"));
        Text title = doc.createTextNode("Introduction to Algorithms");
        titleNode.appendChild(title);
        return titleNode;
    }

    private static void writeToFile(Document doc) throws Exception {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        File xmlFile = new File("/tmp/library.xml");
        StreamResult target = new StreamResult(xmlFile);
        transformer.transform(source, target);
    }
}
