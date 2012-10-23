package de.fiz.karlsruhe.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import org.apache.log4j.Logger;

@ManagedBean(name = "user")
@SessionScoped
public class EsciDocUser implements Serializable {
    
    private Logger logger = Logger.getLogger(EsciDocUser.class);
    private String username;
    private String password;
    private String host;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void valueChangeMethod(ValueChangeEvent e) {
        logger.info("Execute method");
    }
}