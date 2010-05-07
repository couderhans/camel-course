<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:param name="timestamp"/>
  <xsl:template match="/">
    <message>
      <body>Message at <xsl:value-of select="$timestamp"/></body>
    </message>
  </xsl:template>
</xsl:stylesheet>
