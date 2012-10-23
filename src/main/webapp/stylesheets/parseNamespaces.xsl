
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output encoding="utf-8" method="text"/>

    <xsl:template match="/">
        <xsl:apply-templates select="//*|//@*"/>
    </xsl:template>

    <xsl:template match="*|@*">
        <!--<xsl:text>name          = "</xsl:text>
        <xsl:value-of select="name(.)"/>
        <xsl:text>"&#10;</xsl:text>
        <xsl:text>namespace URI = "</xsl:text>
        <xsl:value-of select="namespace-uri(.)"/>
        <xsl:text>"&#10;</xsl:text>
        <xsl:text>This node has </xsl:text>
        <xsl:value-of select="count(./namespace::*)"/>
        <xsl:text> namespace nodes:&#10;</xsl:text>-->
        <xsl:for-each select="./namespace::*">
            <xsl:text></xsl:text>
            <xsl:value-of select="name(.)"/>
            <xsl:text> </xsl:text>
            <xsl:value-of select="."/>
            <xsl:text>"&#10;</xsl:text>
        </xsl:for-each>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>
</xsl:stylesheet>