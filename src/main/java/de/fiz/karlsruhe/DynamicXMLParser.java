package de.fiz.karlsruhe;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DynamicXMLParser {

    private Logger logger = Logger.getLogger(DynamicXMLParser.class);
    
    private OutputStream transform(InputStream xml, URL xslt) {
        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        TransformerFactory tFactory = new net.sf.saxon.TransformerFactoryImpl();
        try {
            Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource(
                    xslt.openConnection().getInputStream()));
            transformer.transform(
                    new javax.xml.transform.stream.StreamSource(xml), new StreamResult(byteArrayOutput));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutput;
    }

    private Map createNamespaceMap(InputStream xmlFile, URL xsltFile) throws MalformedURLException {
        Map<String, String> nsMap = new HashMap<String, String>();

        ByteArrayOutputStream byteArrayOutput = (ByteArrayOutputStream) transform(xmlFile, xsltFile);
        String[] prefix = byteArrayOutput.toString().split("\\n");
        for (int i = 0; i < prefix.length - 1; i++) {
            if (prefix[i].startsWith(" ")) {
                prefix[i] = prefix[i].replaceAll("\\s", "");
                prefix[i] = prefix[i].replaceAll("\"", "");
                nsMap.put(XMLConstants.DEFAULT_NS_PREFIX, prefix[i]);
            } else {
                String p = "", uri = "";
                for (int j = 0; j < prefix[i].indexOf(" "); j++) {
                    p += prefix[i].charAt(j);
                }

                for (int j = prefix[i].indexOf(" ") + 1; j < prefix[i].length() - 1; j++) {
                    uri += prefix[i].charAt(j);
                }
                nsMap.put(p, uri);
            }
        }
        return nsMap;
    }

    public NodeList parseXMLDynamic(String xpath, String xsltURL, String retrievedItems) throws MalformedURLException, XPathExpressionException, FileNotFoundException, UnsupportedEncodingException, IOException {
        InputStream is = new ByteArrayInputStream(retrievedItems.getBytes());
        DynamicXMLParser dynamicParser = new DynamicXMLParser();
        Map<String, String> nsMap1 = new HashMap<String, String>();
        nsMap1 = dynamicParser.createNamespaceMap(is, new URL(xsltURL));
        InputSource is_msMap1 = new InputSource(new StringReader(retrievedItems));
        return new XPath().evaluteXPath(xpath, is_msMap1, nsMap1);
    }
}
