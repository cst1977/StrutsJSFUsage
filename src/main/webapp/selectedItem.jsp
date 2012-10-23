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
        <html:form action="/Mail">
            <h1>You have select following eSciDocItem</h1>
            <div style="padding:16px">
                <div style="float:left;padding-right:8px;">
                    <html:text property="selectedTitle" size="50" value='${cf:retrieveItem(selectForm.escidocItemLabels, selectForm.selectedItem)}' readonly="true" /><br/>
                    <%--<html:text property="xmlSelectItem" size="50" value='${cf:retrieveEscidocXML(selectForm.retrievedItems, cf:retrieveItem(selectForm.escidocItemLabels, selectForm.selectedItem), selectForm.selectedItem)}' readonly="true" style="visibility:hidden"/>
                    <html:text property="familyName" size="50" value='${cf:familyName(cf:retrieveEscidocXML(selectForm.retrievedItems, cf:retrieveItem(selectForm.escidocItemLabels, selectForm.selectedItem), selectForm.selectedItem))}' readonly="true" style="visibility:hidden"/>
                    <html:text property="givenName" size="50" value='${cf:givenName(cf:retrieveEscidocXML(selectForm.retrievedItems, cf:retrieveItem(selectForm.escidocItemLabels, selectForm.selectedItem), selectForm.selectedItem))}' readonly="true" style="visibility:hidden"/>--%>
                    <html:text property="objid" size="50" value='${cf:objID(cf:retrieveEscidocXML(selectForm.retrievedItems, cf:retrieveItem(selectForm.escidocItemLabels, selectForm.selectedItem), selectForm.selectedItem))}' readonly="true" /><br/>
                    <html:text property="concat" size="50" value='${cf:concatList(cf:familyName(cf:retrieveEscidocXML(selectForm.retrievedItems, cf:retrieveItem(selectForm.escidocItemLabels, selectForm.selectedItem), selectForm.selectedItem)), cf:givenName(cf:retrieveEscidocXML(selectForm.retrievedItems, cf:retrieveItem(selectForm.escidocItemLabels, selectForm.selectedItem), selectForm.selectedItem)))}' readonly="true" /><br/>
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