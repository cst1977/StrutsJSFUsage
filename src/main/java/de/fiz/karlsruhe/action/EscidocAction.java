package de.fiz.karlsruhe.action;

import de.fiz.karlsruhe.beans.EsciDocUser;
import de.fiz.karlsruhe.beans.EscidocItem;
import de.fiz.karlsruhe.form.EscidocForm;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EscidocAction extends Action {

    private static final Logger logger = Logger.getLogger(EscidocAction.class);
    private EscidocForm escidocForm;
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("execute method EscidocAction");
        if(form != null) {
            escidocForm = (EscidocForm) form;
            escidocForm.setRetrievedItems((String) request.getSession().getAttribute("retrievedItems"));
        }
        Collection escidocItemLabels = escidocForm.getEscidocItemLabels();
        logger.info("Size of escidocItemLabels: " + escidocItemLabels.size());
        return mapping.findForward("success");
    }
}