package de.fiz.karlsruhe.action;

import de.fiz.karlsruhe.form.UserForm;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

public class UserAction extends Action {

    private static final Logger logger = Logger.getLogger(UserAction.class);
    private UserForm userForm;
    private String host;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("execute method UserAction");
        if(form != null ) {
            userForm = (UserForm)form;
            host = userForm.getHost();
  	}
        request.getSession().setAttribute("host", host);
        response.sendRedirect("logon.faces");
        return null;
        
        //return mapping.findForward("success");
    }
}