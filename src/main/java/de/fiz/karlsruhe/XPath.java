package de.fiz.karlsruhe;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.*;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

public class XPath {

    private Logger logger = Logger.getLogger(XPath.class);
    
    public NodeList evaluteXPath(String xpath, InputSource source, final Map<String, String> nsMap) throws XPathExpressionException {
        logger.info("evaluateXPath: " + xpath);
        final javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList list = null;
        xPath.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(final String namespaceURI) {
                return null;
            }

            @Override
            public String getPrefix(final String namespaceURI) {
                String returnPrefix = "";
                Iterator it = nsMap.entrySet().iterator();
                while (it.hasNext()) {
                    if (it.next().equals(namespaceURI)) {
                        returnPrefix = (String) it.next();
                        break;
                    } else {
                        continue;
                    }
                }
                return returnPrefix;
            }

            @Override
            public String getNamespaceURI(String prefix) {
                String uri = null;
                Iterator it = nsMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry) it.next();
                    if (prefix.equals(pairs.getKey())) {
                        uri = (String) pairs.getValue();
                    }
                }
                return uri;
            }
        });
        list = (NodeList) xPath.evaluate(xpath, source, XPathConstants.NODESET);
        return list;
    }
}