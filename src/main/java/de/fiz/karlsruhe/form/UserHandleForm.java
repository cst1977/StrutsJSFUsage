package de.fiz.karlsruhe.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class UserHandleForm extends ActionForm {

    private Logger logger = Logger.getLogger(UserHandleForm.class);
    private String userHandle;
    
    public String getUserHandle() {
        return userHandle;
    }
    
    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }
}
