package de.fiz.karlsruhe.form;

import org.apache.struts.action.ActionForm;

public class MailForm extends ActionForm {

    private String selectedTitle;
    private String xmlSelectItem;
    private String familyName;
    private String givenName;
    private String objid;
    private String concat;

    public String getConcat() {
        return concat;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getObjid() {
        return objid;
    }

    public String getSelectedTitle() {
        return selectedTitle;
    }

    public String getXmlSelectItem() {
        return xmlSelectItem;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    public void setSelectedTitle(String selectedTitle) {
        this.selectedTitle = selectedTitle;
    }

    public void setXmlSelectItem(String xmlSelectItem) {
        this.xmlSelectItem = xmlSelectItem;
    }
}
