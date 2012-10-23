<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html>
    <head>
    </head>
    <body>
        <h1>Please input the Hostname on which your eSciDoc-Infrastructure is running</h1>
        <html:form action="/Select">
            <h2>Please select an escidocItem from the select box</h2>
            <div style="padding:16px">
                <div style="float:left;padding-right:8px;"> 
                    <html:select property="selectedItem">
                        <html:options property="escidocItemValues" labelProperty="escidocItemLabels" />
                    </html:select>
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