<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-faces" %>

<f:view>
    <s:html locale="true">
        <head>
            <title>Logon to eSciDoc-Core</title>
        </head>
        <body>
            <p />
            <s:form id="logon" action="/Logon">
                <h2>Please login to eSciDoc-Core</h2>
                <h:outputLabel for="username" value="Username:" />
                <h:inputText id="username" value="#{logonForm.username}" required="true" size="15" /> <p/>
                <h:outputLabel for="password" value="Password:" />
                <h:inputSecret id="password" value="#{logonForm.password}" required="true" size="15" /> <p />
                <h:commandButton id="sumit" type="SUBMIT" value="Logon" />
            </s:form>
        </body>
    </s:html>
</f:view>
