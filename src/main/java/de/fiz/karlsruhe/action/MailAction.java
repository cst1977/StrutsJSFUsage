package de.fiz.karlsruhe.action;

import de.fiz.karlsruhe.form.MailForm;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

public class MailAction extends Action {

    private static final Logger logger = Logger.getLogger(MailAction.class);
    private MailForm mailForm;
    private String selectedTitle;
    private String objid;
    private String concat;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("execute method MailAction");
        if(form != null) {
            mailForm = (MailForm) form;
            selectedTitle = mailForm.getSelectedTitle();
            objid = mailForm.getObjid();
            concat = mailForm.getConcat();
            logger.info("selectedTitle: " + selectedTitle);
            logger.info("objid: " + objid);
            logger.info("concat Family-Given Name: " + concat);
        }
        request.getSession().setAttribute("selectedTitle", selectedTitle);
        request.getSession().setAttribute("objid", objid);
        request.getSession().setAttribute("concat", concat);
        return mapping.findForward("success");
    }
}