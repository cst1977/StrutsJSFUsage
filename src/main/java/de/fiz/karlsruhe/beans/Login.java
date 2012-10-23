package de.fiz.karlsruhe.beans;

import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.xerces.impl.dv.util.Base64;

public class Login {

    public Login(HttpClient httpClient, String frameworkUrl) {
        FRAMEWORK_URL = frameworkUrl;
        client = httpClient;
    }

    private Cookie passSecurityCheck() {
        int delim1 = FRAMEWORK_URL.indexOf("//");
        int delim2 = FRAMEWORK_URL.indexOf(":", delim1);
        String host;
        int port;
        if (delim2 > 0) {
            host = FRAMEWORK_URL.substring(delim1 + 2, delim2);
            port = Integer.parseInt(FRAMEWORK_URL.substring(delim2 + 1));
        } else {
            host = FRAMEWORK_URL.substring(delim1 + 2);
            port = 80;
        }
        PostMethod post = new PostMethod((new StringBuilder()).append(FRAMEWORK_URL).append("/aa/j_spring_security_check").toString());
        post.addParameter("j_username", loginName);
        post.addParameter("j_password", loginPassword);
        try {
            client.executeMethod(post);
        } catch (HttpException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        post.releaseConnection();
        CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
        Cookie logoncookies[] = cookiespec.match(host, port, "/", false, client.getState().getCookies());
        Cookie sessionCookie = logoncookies[0];
        return sessionCookie;
    }

    private String passLogin(Cookie securityCookie) {
        String userHandle = null;
        PostMethod postMethod = new PostMethod((new StringBuilder()).append(FRAMEWORK_URL).append("/aa/login").toString());
        postMethod.addParameter("target", FRAMEWORK_URL);
        client.getState().addCookie(securityCookie);
        try {
            client.executeMethod(postMethod);
        } catch (HttpException e) {
            System.out.println("HttpException in login POST request");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException in login POST request");
            e.printStackTrace();
        }
        if (postMethod.getStatusCode() != 303) {
            System.out.println((new StringBuilder()).append("StatusCode: ").append(postMethod.getStatusCode()).toString());
            return userHandle;
        }
        java.io.InputStream is;
        try {
            is = postMethod.getResponseBodyAsStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Header headers[] = postMethod.getResponseHeaders();
        for (int i = 0; i < headers.length; i++) {
            if ("Location".equals(headers[i].getName())) {
                String location = headers[i].getValue();
                int index = location.indexOf('=');
                userHandle = new String(Base64.decode(location.substring(index + 1, location.length())));
            }
        }

        return userHandle;
    }

    public Cookie getSessionCookie() {
        return passSecurityCheck();
    }

    public String getUserHandle() {
        return passLogin(passSecurityCheck());
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    private final String FRAMEWORK_URL;
    private HttpClient client;
    private String loginName;
    private String loginPassword;
}