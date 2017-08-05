package coo.core.config;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import coo.base.constants.Encoding;
import coo.base.util.DateUtils;

@ConfigurationProperties(prefix = FreeMarkerProperties.PREFIX)
public class FreeMarkerProperties {
  public static final String PREFIX = "coo.freemarker";
  private Integer templateUpdateDelay = 5;
  private String urlEscapingCharset = Encoding.UTF_8;
  private String defaultEncoding = Encoding.UTF_8;
  private String outputEncoding = Encoding.UTF_8;
  private String locale = "";
  private String datetimeFormat = DateUtils.SECOND;
  private String dateFormat = DateUtils.DAY;
  private String timeFormat = "HH:mm:ss";
  private String numberFormat = "#";
  private String booleanFormat = "true,false";
  private Boolean classicCompatible = true;
  private Boolean whitespaceStripping = true;

  public Properties toProperties() {
    Properties properties = new Properties();
    properties.put("template_update_delay", templateUpdateDelay.toString());
    properties.put("url_escaping_charset", urlEscapingCharset);
    properties.put("default_encoding", defaultEncoding);
    properties.put("output_encoding", outputEncoding);
    properties.put("locale", locale);
    properties.put("datetime_format", datetimeFormat);
    properties.put("date_format", dateFormat);
    properties.put("time_format", timeFormat);
    properties.put("number_format", numberFormat);
    properties.put("boolean_format", booleanFormat);
    properties.put("classic_compatible", classicCompatible.toString());
    properties.put("whitespace_stripping", whitespaceStripping.toString());
    return properties;
  }

  public Integer getTemplateUpdateDelay() {
    return templateUpdateDelay;
  }

  public void setTemplateUpdateDelay(Integer templateUpdateDelay) {
    this.templateUpdateDelay = templateUpdateDelay;
  }

  public String getUrlEscapingCharset() {
    return urlEscapingCharset;
  }

  public void setUrlEscapingCharset(String urlEscapingCharset) {
    this.urlEscapingCharset = urlEscapingCharset;
  }

  public String getDefaultEncoding() {
    return defaultEncoding;
  }

  public void setDefaultEncoding(String defaultEncoding) {
    this.defaultEncoding = defaultEncoding;
  }

  public String getOutputEncoding() {
    return outputEncoding;
  }

  public void setOutputEncoding(String outputEncoding) {
    this.outputEncoding = outputEncoding;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public String getDatetimeFormat() {
    return datetimeFormat;
  }

  public void setDatetimeFormat(String datetimeFormat) {
    this.datetimeFormat = datetimeFormat;
  }

  public String getDateFormat() {
    return dateFormat;
  }

  public void setDateFormat(String dateFormat) {
    this.dateFormat = dateFormat;
  }

  public String getTimeFormat() {
    return timeFormat;
  }

  public void setTimeFormat(String timeFormat) {
    this.timeFormat = timeFormat;
  }

  public String getNumberFormat() {
    return numberFormat;
  }

  public void setNumberFormat(String numberFormat) {
    this.numberFormat = numberFormat;
  }

  public String getBooleanFormat() {
    return booleanFormat;
  }

  public void setBooleanFormat(String booleanFormat) {
    this.booleanFormat = booleanFormat;
  }

  public Boolean getClassicCompatible() {
    return classicCompatible;
  }

  public void setClassicCompatible(Boolean classicCompatible) {
    this.classicCompatible = classicCompatible;
  }

  public Boolean getWhitespaceStripping() {
    return whitespaceStripping;
  }

  public void setWhitespaceStripping(Boolean whitespaceStripping) {
    this.whitespaceStripping = whitespaceStripping;
  }
}
