package de.fiz.karlsruhe.custom.functions;

import de.fiz.karlsruhe.DynamicXMLParser;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Functions {

    private static Logger logger = Logger.getLogger(Functions.class);
    private static int length;
    private static Object result;
    private static String dcTitle;
    private static List<String> familyName;
    private static List<String> givenName;
    
    public static String retrieveEscidocXML(String retrievedItem, String selectedItem, String value) throws IOException, MalformedURLException, XPathExpressionException, TransformerConfigurationException, TransformerException {
        DynamicXMLParser xmlDynamicParser = new DynamicXMLParser();
        String xpath = "//escidocItem:item";
        String xsltURL = "http://localhost:8080/StrutsJSFUsage/stylesheets/parseNamespaces.xsl";
        logger.info("xsltURL: " + xsltURL);
        NodeList nodeList = xmlDynamicParser.parseXMLDynamic(xpath, xsltURL, retrievedItem);
        logger.info("Functions selectedItem: " + value);
        Node item = nodeList.item(Integer.parseInt(value));
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        StringWriter buffer = new StringWriter();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(item), new StreamResult(buffer));
        String str = buffer.toString();
        return str;
    }
    
    public static List<String> familyName(String retrievedItems) throws MalformedURLException, XPathExpressionException, FileNotFoundException, UnsupportedEncodingException, IOException {
        DynamicXMLParser xmlDynamicParser = new DynamicXMLParser();
        List<String> temp = new ArrayList<String>();
        String xsltURL = "http://localhost:8080/StrutsJSFUsage/stylesheets/parseNamespaces.xsl";
        NodeList nodeList = xmlDynamicParser.parseXMLDynamic("//publication:publication/eterms:creator/person:person/eterms:family-name", xsltURL, retrievedItems);
        for(int index = 0; index < nodeList.getLength(); index++) {
            temp.add(nodeList.item(index).getTextContent());
        }
        return temp;
    }
    
    public static List<String> givenName(String retrievedItems) throws MalformedURLException, XPathExpressionException, FileNotFoundException, UnsupportedEncodingException, IOException {
        DynamicXMLParser xmlDynamicParser = new DynamicXMLParser();
        List<String> temp = new ArrayList<String>();
        String xsltURL = "http://localhost:8080/StrutsJSFUsage/stylesheets/parseNamespaces.xsl";
        NodeList nodeList = xmlDynamicParser.parseXMLDynamic("//publication:publication/eterms:creator/person:person/eterms:given-name", xsltURL, retrievedItems);
        for(int index = 0; index < nodeList.getLength(); index++) {
            temp.add(nodeList.item(index).getTextContent());
        }
        return temp;
    }
    
    public static String objID(String retrievedItems) throws MalformedURLException, XPathExpressionException, FileNotFoundException, UnsupportedEncodingException, IOException {
        DynamicXMLParser xmlDynamicParser = new DynamicXMLParser();
        String xsltURL = "http://localhost:8080/StrutsJSFUsage/stylesheets/parseNamespaces.xsl";
        NodeList nodeList = xmlDynamicParser.parseXMLDynamic("//escidocItem:item/@objid", xsltURL, retrievedItems);
        return nodeList.item(0).getTextContent();
    }

    public static List concatList(java.lang.Object col1, java.lang.Object col2) {
        logger.info("execute concatList");
        List<String> temp = new ArrayList<String>();
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        list1.add(col1);
        list2.add(col2);
        int lowest = 0;
        
        if(list1.size() > list2.size()) {
            lowest = list2.size();
        }
        else if(list1.size() < list2.size()) {
            lowest = list1.size();
        }
        else {
            lowest = list1.size();
        }
        for(int index = 0; index < lowest; index++) {
            temp.add(list1.get(index) + ", " + list2.get(index));
        }
        return temp;
    }

    public static String retrieveItem(Object obj, String value) {
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            Object[] collectionArray = collection.toArray();
            try {
                length = Integer.parseInt(value);
                logger.info("Parameter value length: " + length);
            } catch (NumberFormatException ex) {
                logger.error("Value could no be parsed to int", ex);
            }
            result = collectionArray[length];
        }
        if (result instanceof String) {
            return (String) result;
        } else {
            return "";
        }
    }
}
