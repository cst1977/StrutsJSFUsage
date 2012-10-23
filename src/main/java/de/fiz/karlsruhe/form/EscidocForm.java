/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fiz.karlsruhe.form;

import de.fiz.karlsruhe.beans.EscidocItem;
import java.util.Collection;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

public class EscidocForm extends ActionForm {
    
    private static Logger logger = Logger.getLogger(EscidocForm.class);
    private String selectedItem;
    private Collection escidocItemLabels = null;
    private Collection escidocItemValues = null;
    private String retrievedItems;

    public void setRetrievedItems(String retrievedItems) {
        this.retrievedItems = retrievedItems;
    }

    public String getRetrievedItems() {
        return retrievedItems;
    }
    
    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public Collection getEscidocItemLabels() {
        
        escidocItemLabels = EscidocItem.getListDCLabels();
        return escidocItemLabels;
    }

    public void setEscidocItemLabels(Collection escidocItemLabels) {
        this.escidocItemLabels = escidocItemLabels;
    }

    public void setEscidocItemValues(Collection escidocItemValues) {
        this.escidocItemValues = escidocItemValues;
    }

    public Collection getEscidocItemValues() {
        escidocItemValues = EscidocItem.getListDCValues();
        return escidocItemValues;
    }
}