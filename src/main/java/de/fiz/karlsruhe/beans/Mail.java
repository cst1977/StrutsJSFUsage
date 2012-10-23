package de.fiz.karlsruhe.beans;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;
import javax.mail.*;

public class Mail {

    private String SMTP_HOST_NAME;
    private String SMTP_AUTH_USER;
    private String SMTP_AUTH_PWD;

    public void sendMail(Properties props, String recipient, String subject, String content) throws Exception {

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(props, auth);
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);

        Multipart multipart = new MimeMultipart("alternative");

        BodyPart part2 = new MimeBodyPart();
        part2.setContent(content, "text/html");

        multipart.addBodyPart(part2);

        message.setContent(multipart);
        message.setFrom(new InternetAddress(recipient));
        message.setSubject(subject);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        transport.connect();
        transport.sendMessage(message,
                message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
        
    public String getSMTP_AUTH_PWD() {
        return SMTP_AUTH_PWD;
    }

    public String getSMTP_AUTH_USER() {
        return SMTP_AUTH_USER;
    }

    public String getSMTP_HOST_NAME() {
        return SMTP_HOST_NAME;
    }

    public void setSMTP_AUTH_PWD(String SMTP_AUTH_PWD) {
        this.SMTP_AUTH_PWD = SMTP_AUTH_PWD;
    }

    public void setSMTP_AUTH_USER(String SMTP_AUTH_USER) {
        this.SMTP_AUTH_USER = SMTP_AUTH_USER;
    }

    public void setSMTP_HOST_NAME(String SMTP_HOST_NAME) {
        this.SMTP_HOST_NAME = SMTP_HOST_NAME;
    }
}
