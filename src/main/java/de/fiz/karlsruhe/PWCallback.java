package de.fiz.karlsruhe;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.ws.security.WSPasswordCallback;

/**
 * PWCallback for the Client.<p/>
 *
 * Sets the eSciDoc user handle as the password if the technical username
 * "eSciDocUser" is provided when calling the webservice.<br> This password can
 * be changed by using the
 * <code>setHandle</code> and reset by using the
 * <code>resetHandle</code> methods
 *
 * @aa
 */
public class PWCallback implements CallbackHandler {

    private static String handle = null;

    /**
     * Gets the eSciDoc user handle.
     *
     * @return The user handle.
     */
    public static String getHandle() {

        return handle;
    }

    /**
     * Sets the eSciDoc user handle to the provided value.
     *
     * @param hd The eSciDoc user handle to use.
     */
    public static void setHandle(final String hd) {

        handle = hd;
    }

    /**
     * The handle method of the callback handler.
     *
     * @param callbacks the WSPasswordCallback implementation
     * @throws IOException Exception
     * @throws UnsupportedCallbackException Exception
     * @see javax.security.auth.callback.CallbackHandler#handle
     * (javax.security.auth.callback.Callback[]) @aa
     */
    public void handle(final Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof WSPasswordCallback) {
                WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
                if ("eSciDocUser".equals(pc.getIdentifer())) {
                    pc.setPassword(handle);
                }
            } else {
                throw new UnsupportedCallbackException(callbacks[i],
                        "Unrecognized Callback");
            }
        }
    }

    /**
     * Adds cookie escidocCookie storing the eSciDoc user handle as the content
     * of the cookie escidocCookie to to the provided http method object.<br>
     * The adding is skipped, if the current user handle is
     * <code>null</code> or equals to an empty
     * <code>String</code>.
     *
     * @param method The http method object to add the cookie to.
     */
    public static void addEscidocUserHandleCokie(final HttpMethod method) {

        if (handle == null || "".equals(handle)) {
            return;
        }
        method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        method.setRequestHeader("Cookie", "escidocCookie=" + handle);
    }
}