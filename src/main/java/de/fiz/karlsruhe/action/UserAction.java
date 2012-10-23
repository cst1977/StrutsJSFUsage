package de.fiz.karlsruhe.action;

import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

public class UserAction extends Action {

    private static final Logger logger = Logger.getLogger(UserAction.class);
    private String userHandle;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("execute method UserAction");
        Cookie[] cookies = request.getCookies();
        for(int index = 0; index < cookies.length; index++) {
            Cookie cookie = cookies[index];
            if(cookie.getName().equals("escidocCookie")) {
                userHandle = cookie.getValue();
                logger.info("userHandle: " + userHandle);
            }
        }
        request.getSession().setAttribute("userHandle", userHandle);
        return mapping.findForward("success");
    }
}