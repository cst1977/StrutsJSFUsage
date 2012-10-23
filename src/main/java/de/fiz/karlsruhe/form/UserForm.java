package de.fiz.karlsruhe.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;

public class UserForm extends ActionForm {

    private String host;
   
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getHost() == null || "".equals(getHost())) {
            errors.add("common.name.err", new ActionMessage("error.common.host.required"));
        }
        return errors;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        host = "";
    }
}
