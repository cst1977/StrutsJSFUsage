<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"     uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cf"    uri="http://fiz.de/karlsruhe/custom/functions" %>
<%@ taglib prefix="html"  uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="bean"  uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="s"     uri="/WEB-INF/struts-tags.tld" %>

<html>
    <head>
    </head>
    <body>
        <html:form action="/UserHandle">
            <h1>Logged In</h1>
            <div style="padding:16px">
                <div style="float:left;padding-right:8px;">
                    <html:text property="userHandle" size="50" value='${userHandleForm.userHandle}'/><br/>
                </div>
            </div>
            <div style="padding:16px">
                <div style="float:left;padding-right:8px;">
                    <html:submit><bean:message key="label.common.button.submit" /></html:submit>
                </div>
            </div>
        </html:form>
    </body>
</html>