package de.fiz.karlsruhe.action;

import de.fiz.karlsruhe.form.UserHandleForm;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

public class UserHandleAction extends Action {

    private static final Logger logger = Logger.getLogger(UserHandleAction.class);
    private String userHandle;
    private UserHandleForm userHandleForm;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("execute method UserHandleAction");
        if(form != null) {
            userHandleForm = (UserHandleForm)form;
            userHandleForm.setUserHandle((String)request.getSession().getAttribute("userHandle"));
        }
        return mapping.findForward("success");
    }
}