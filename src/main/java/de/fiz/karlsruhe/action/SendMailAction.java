package de.fiz.karlsruhe.action;

import de.fiz.karlsruhe.beans.Mail;
import de.fiz.karlsruhe.form.SendMailForm;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SendMailAction extends Action {

    private static final Logger logger = Logger.getLogger(MailAction.class);
    private SendMailForm sendMailForm;
    private Mail mail;
    private String mailAddress;
    private String selectedTitle;
    private String objid;
    private String concat;
    private static Properties createMailProperties(String protocol, String hostName, int port, boolean auth) {
        Properties props = new Properties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.host", hostName);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);
        return props;
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("SendMailAction executed");
        mail = new Mail();
        if(form != null) {
            sendMailForm = (SendMailForm) form;
            mailAddress = sendMailForm.getMailAddress();
            concat = (String) request.getSession().getAttribute("concat");
            objid = (String) request.getSession().getAttribute("objid");
            selectedTitle = (String) request.getSession().getAttribute("selectedTitle");
        }
        logger.info("send E-Mail to: " + mailAddress);
        Properties mailProperties = createMailProperties("smtp", "localhost", 587, true);
        String subject = "Genehmigung für Publikation: " + selectedTitle;
        String content = "request Attrbutes: objid: " + objid + " concat: " + concat;
        mail.sendMail(mailProperties, mailAddress, subject, content);
        return mapping.findForward("success");
    }
}
