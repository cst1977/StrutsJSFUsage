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
        FacesContext context = FacesContext.getCurrentInstance();
        logger.info("FacesContext: " + context);
        return mapping.findForward("success");
    }
}