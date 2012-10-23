<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cf" uri="http://fiz.de/karlsruhe/custom/functions" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
    </head>

    <body>
        <h1>Struts 2 set tag example</h1>
        <h4>1. &lt;s:set var="varMsg" value="msg" /&gt;</h4>
        <s:set var="varMsg" value="msg" />
        <s:property value="varMsg" />

        <h4>3. &lt;s:set var="varUrl" value="%{'http://www.mkyong.com'}" /&gt;</h4> 
        <s:set var="varUrl" value="%{'http://www.mkyong.com'}" />
        <s:property value="varUrl" />
        <html:form action="/Mail">
            <h1>You have select following eSciDocItem</h1>
            <div style="padding:16px">
                <div style="float:left;padding-right:8px;">
                    <html:submit><bean:message key="label.common.button.submit" /></html:submit>
                </div>
            </div>
        </html:form>

    </body>
</html>