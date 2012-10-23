package de.fiz.karlsruhe.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

public class SetTagAction extends ActionSupport {

    private Logger logger = Logger.getLogger(SetTagAction.class);
    private String msg = "Struts 2 is a funny framework";

    public String getMsg() {
        logger.info("getMessage Method: " + msg);
        return msg;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }
}