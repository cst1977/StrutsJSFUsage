package de.fiz.karlsruhe.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LogonForm extends ActionForm {

    private Logger logger = Logger.getLogger(LogonForm.class);
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();

        if (getUsername() == null || ("".equals(getUsername()))) {
            errors.add("common.name.err", new ActionMessage("error.common.name.required"));
        }

        return errors;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        username = "";
        password = "";
    }
}
