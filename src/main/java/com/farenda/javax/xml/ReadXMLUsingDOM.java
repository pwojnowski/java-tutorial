package com.farenda.javax.xml;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ReadXMLUsingDOM {

    public static void main(String[] args) throws Exception {
        String filename = "/tmp/library.xml";

        Document library = loadXML(filename);

        // "books" is the root element:
        Node books = library.getFirstChild();
        displayBooks(books);

        Element newBook = createBook(library);
        books.appendChild(newBook);

        writeToFile(filename, library);
    }

    private static Document loadXML(String filename) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.parse(new File(filename));
    }

    private static void displayBooks(Node books) {
        NodeList childNodes = books.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            Node book = childNodes.item(i);
            System.out.printf("%s: %s%n",
                    book.getNodeName(),
                    book.getTextContent());
        }
    }

    private static Element createBook(Document doc) {
        Element book = doc.createElement("book");
        book.appendChild(createTitleNode(doc));
        return book;
    }

    private static Element createTitleNode(Document doc) {
        Element titleNode = doc.createElement("title");
        Text title = doc.createTextNode("The passionate programmer");
        titleNode.appendChild(title);
        return titleNode;
    }

    private static void writeToFile(String filename, Document doc) throws Exception {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        File xmlFile = new File(filename);
        StreamResult target = new StreamResult(xmlFile);
        transformer.transform(source, target);
    }

}
