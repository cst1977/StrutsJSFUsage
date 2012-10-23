package de.fiz.karlsruhe.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "escidocItem")
@SessionScoped
public class EscidocItem extends org.apache.struts.action.ActionForm implements Serializable {

    private static Logger logger = Logger.getLogger(EscidocItem.class);
    private static Collection listDCLabels = new ArrayList();
    private static Collection listDCValues = new ArrayList();
    
    public static Collection getListDCLabels() {
        return listDCLabels;
    }

    public static Collection getListDCValues() {
        return listDCValues;
    }

    public void setListDCLabels(Collection listDCLabels) {
        this.listDCLabels = listDCLabels;
    }

    public void setListDCValues(Collection listDCValues) {
        this.listDCValues = listDCValues;
    }
}
