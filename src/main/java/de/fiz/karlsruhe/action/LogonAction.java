package de.fiz.karlsruhe.action;

import de.escidoc.schemas.itemlist._0.ItemList;
import de.fiz.karlsruhe.DynamicXMLParser;
import de.fiz.karlsruhe.beans.EsciDocUser;
import de.fiz.karlsruhe.beans.EscidocItem;
import de.fiz.karlsruhe.beans.RetrieveItemBean;
import de.fiz.karlsruhe.form.EscidocForm;
import de.fiz.karlsruhe.form.LogonForm;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.w3c.dom.NodeList;

public class LogonAction extends Action {

    private static Logger logger = Logger.getLogger(LogonAction.class);
    private RetrieveItemBean retrieveItem = new RetrieveItemBean();
    private LogonForm logonForm;
    private String filter;
    private String username;
    private String password;
    private String host;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("execute method LogonAction");
        if(form != null) {
            logonForm = (LogonForm)form;
            username = logonForm.getUsername();
            password = logonForm.getPassword();
            host = (String) request.getSession().getAttribute("host");
            logger.info("Host: " + host);
        }
        EsciDocUser eSciDocUser = new EsciDocUser();
        eSciDocUser.setUsername(username);

        /*
         * Different method to get the Input Property
         * 
         * import org.apache.commons.beanutils.PropertyUtils;
         * (String) PropertyUtils.getSimpleProperty(form, "password")
         */
        eSciDocUser.setPassword(password);
        eSciDocUser.setHost(host);
        logger.info("Current eSciDoc-User: " + eSciDocUser);
        filter = "<param>" + 
                    "<filter name=\"http://escidoc.de/core/01/properties/public-status\">" + 
                        "pending" + 
                    "</filter>" + 
                 "</param>";
        String retrievedItems = retrieveItem.retrieveItems(eSciDocUser, filter);
        InputStream is = new ByteArrayInputStream(retrievedItems.getBytes());
        DynamicXMLParser xmlDynamicParser = new DynamicXMLParser();
        String xpath = "//publication:publication/dc:title";
        String xsltURL = request.getScheme() + 
                         "://" + request.getServerName() + 
                         ":" + request.getServerPort() + 
                         "/" + request.getContextPath() + 
                         "/stylesheets/parseNamespaces.xsl";
        logger.info("xsltURL: " + xsltURL);
        NodeList nodeList = xmlDynamicParser.parseXMLDynamic(xpath, xsltURL, retrievedItems);
        for (int index = 0; index < nodeList.getLength(); index++) {
            EscidocItem.getListDCLabels().add(nodeList.item(index).getTextContent());
            EscidocItem.getListDCValues().add(index);
        }
        //ItemList escidocItemList = retrieveItem.unMarshallXML(is);
        FacesContext context = FacesContext.getCurrentInstance();
        request.getSession().setAttribute("retrievedItems", retrievedItems);
        logger.info("FacesContext: " + context);
        return mapping.findForward("success");
    }
}