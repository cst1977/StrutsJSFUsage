package de.fiz.karlsruhe.beans;

import de.escidoc.schemas.components._0.Component;
import de.escidoc.schemas.components._0.Components;
import de.escidoc.schemas.item._0.Item;
import de.escidoc.schemas.itemlist._0.ItemList;
import de.escidoc.schemas.metadatarecords._0.MdRecord;
import de.escidoc.schemas.metadatarecords._0.MdRecords;
import de.escidoc.www.services.om.ItemHandler;
import de.escidoc.www.services.om.ItemHandlerServiceLocator;
import de.fiz.karlsruhe.PWCallback;
import de.fiz.karlsruhe.XPath;
import de.fiz.karlsruhe.mapper.OxMapper;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;
import javax.swing.text.Document;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import org.apache.axis.configuration.FileProvider;
import org.apache.commons.httpclient.HttpClient;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RetrieveItemBean {

    private static Logger logger = Logger.getLogger(RetrieveItemBean.class);
    private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("example-context.xml");
    private OxMapper oxMapper = (OxMapper) context.getBean("oxMapper");
    private ItemHandler itemHandler;

    public String retrieveItems(EsciDocUser eSciDocUser, String filter)
            throws ServiceException, RemoteException,
            FileNotFoundException, IOException,
            ParserConfigurationException, SAXException,
            TransformerConfigurationException,
            TransformerException {
        PWCallback.setHandle(getUserHandle(eSciDocUser.getHost(), eSciDocUser.getUsername(), eSciDocUser.getPassword()));
        return getItemHandler().retrieveItems(filter);
    }

    private ItemHandler getItemHandler()
            throws ServiceException {
        if (itemHandler == null) {
            ItemHandlerServiceLocator serviceLocator = new ItemHandlerServiceLocator(new FileProvider("client.wsdd"));
            serviceLocator.setItemHandlerServiceEndpointAddress("http://localhost:8080/axis/services/ItemHandlerService");
            itemHandler = serviceLocator.getItemHandlerService();
        }
        return itemHandler;
    }

    private String getUserHandle(String host, String name, String password) {
        Login eSciDocCoreLogin;
        if (!host.startsWith("http")) {
            eSciDocCoreLogin = new Login(new HttpClient(), (new StringBuilder()).append("http://").append(host).toString());
        } else {
            eSciDocCoreLogin = new Login(new HttpClient(), host);
        }
        eSciDocCoreLogin.setLoginName(name);
        eSciDocCoreLogin.setLoginPassword(password);
        return eSciDocCoreLogin.getUserHandle();
    }

    public ItemList unMarshallXML(InputStream in, String xpathString) throws IOException,
            ParserConfigurationException,
            SAXException,
            TransformerConfigurationException,
            TransformerException,
            XPathExpressionException {
        Object readObjectFromXml = oxMapper.readObjectFromXml(in);
        ItemList itemList = null;
        try {
            if (readObjectFromXml instanceof ItemList) {
                itemList = (ItemList) readObjectFromXml;
            }
        } catch (ClassCastException ex) {
            logger.error("unmarshalling failed", ex);
        }
        List<Item> item = itemList.getItem();
        logger.info("Item size: " + item.size());
        XPath xpath = new XPath();
        int loopCounter = 0;
        for (Item itemTemp : item) {
            MdRecords mdRecords = itemTemp.getMdRecords();
            List<MdRecord> mdRecord = mdRecords.getMdRecord();
            for (MdRecord tempMdRecord : mdRecord) {
                Element any = tempMdRecord.getAny();
                Map<String, String> nsMap = new HashMap<String, String>();
                nsMap.put("escidocItem", "http://www.escidoc.de/schemas/item/0.9");
                nsMap.put("publication", "http://purl.org/escidoc/metadata/profiles/0.1/publication");
                nsMap.put("dc", "http://purl.org/dc/elements/1.1/");
                nsMap.put("eterms", "http://purl.org/escidoc/metadata/terms/0.1/");
                nsMap.put("person", "http://purl.org/escidoc/metadata/profiles/0.1/person");
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                elementToStream(any, outputStream);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                InputSource inputSource = new InputSource(inputStream);
                NodeList list = xpath.evaluteXPath(xpathString, inputSource, nsMap);
                for (int index = 0; index < list.getLength(); index++) {
                    EscidocItem.getListDCLabels().add(list.item(index).getTextContent() + ", " + itemTemp.getObjid());
                }
                EscidocItem.getListDCValues().add(new Integer(loopCounter++));
            }
            Components components = itemTemp.getComponents();
            List<Component> component = components.getComponent();
            for (Component tempComponent : component) {
                de.escidoc.schemas.components._0.Properties componentProperties = tempComponent.getProperties();
                logger.info("File Name: " + componentProperties.getFileName());
            }
        }
        return itemList;
    }

    public void elementToStream(Element element, OutputStream out) {
        try {
            DOMSource source = new DOMSource(element);
            StreamResult result = new StreamResult(out);
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            transformer.transform(source, result);
        } catch (Exception ex) {
        }
    }
}
