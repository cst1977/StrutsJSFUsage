package de.fiz.karlsruhe;

import de.escidoc.www.services.om.ItemHandler;
import de.escidoc.www.services.om.ItemHandlerServiceLocator;
import de.fiz.karlsruhe.beans.EsciDocUser;
import de.fiz.karlsruhe.beans.Login;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.axis.configuration.FileProvider;
import org.apache.commons.httpclient.HttpClient;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class ItemRetrieverTest {
    
    private static Logger logger = Logger.getLogger(ItemRetrieverTest.class);
    private ItemHandler itemHandler;
    
    private void retrieveItems(EsciDocUser eSciDocUser)
            throws ServiceException, RemoteException, FileNotFoundException, IOException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException {
        String filter = "<param><filter name=\"http://escidoc.de/core/01/properties/public-status\">pending</filter></param>";
        PWCallback.setHandle(getUserHandle(eSciDocUser.getHost(), eSciDocUser.getUsername(), eSciDocUser.getPassword()));
        String retrieveItems = getItemHandler().retrieveItems(filter);
    }
    
    private static String getUserHandle(String host, String name, String password) {
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
    
    private ItemHandler getItemHandler()
            throws ServiceException {
        if (itemHandler == null) {
            ItemHandlerServiceLocator serviceLocator = new ItemHandlerServiceLocator(new FileProvider("client.wsdd"));
            serviceLocator.setItemHandlerServiceEndpointAddress("http://localhost:8080/axis/services/ItemHandlerService");
            itemHandler = serviceLocator.getItemHandlerService();
        }
        return itemHandler;
    }
    
    private Properties loadProperties(String path, final String file) {
        final Properties props = new Properties();
        try {
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.concat(file));
            props.load(resourceAsStream);
        }
        catch(IOException ex) {
            logger.error("Error loading properties file", ex);
        }
        
        return props;
    }
    
    @Test
    public void itemRetriever() throws ServiceException, RemoteException, 
                                       FileNotFoundException, IOException, 
                                       ParserConfigurationException, 
                                       SAXException, 
                                       TransformerConfigurationException, 
                                       TransformerException {
        
        ItemRetrieverTest test = new ItemRetrieverTest();
        Properties loadProperties = test.loadProperties("", "login.properties");
        String escidocUserProperty = loadProperties.getProperty("escidoc.username");
        String escidocPassProperty = loadProperties.getProperty("escidoc.password");        
        String escidocHostProperty = loadProperties.getProperty("escidoc.host");
        String escidocPortProperty = loadProperties.getProperty("escidoc.port");
        EsciDocUser eSciDocUser = new EsciDocUser();
        eSciDocUser.setHost(escidocHostProperty + ":" + escidocPortProperty);
        eSciDocUser.setUsername(escidocUserProperty);
        eSciDocUser.setPassword(escidocPassProperty);
        test.retrieveItems(eSciDocUser);
    }
}