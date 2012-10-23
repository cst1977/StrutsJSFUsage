package de.fiz.karlsruhe.form;

import org.apache.struts.action.ActionForm;

public class SendMailForm extends ActionForm {

    private String mailAddress;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
