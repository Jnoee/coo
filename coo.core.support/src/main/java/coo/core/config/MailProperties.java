package coo.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("coo.mail")
public class MailProperties {
  private String host = "smtp.163.com";
  private Integer port = 25;
  private String username = "coo_mail_test";
  private String password = "coomailtest";
  private String defaultEncoding = "UTF-8";
  private Boolean debug = false;
  private Smtp smtp = new Smtp();

  public static class Smtp {
    private Boolean auth = true;
    private Boolean starttlsEnable = false;
    private Integer timeout = 30000;

    public Boolean getAuth() {
      return auth;
    }

    public void setAuth(Boolean auth) {
      this.auth = auth;
    }

    public Boolean getStarttlsEnable() {
      return starttlsEnable;
    }

    public void setStarttlsEnable(Boolean starttlsEnable) {
      this.starttlsEnable = starttlsEnable;
    }

    public Integer getTimeout() {
      return timeout;
    }

    public void setTimeout(Integer timeout) {
      this.timeout = timeout;
    }
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDefaultEncoding() {
    return defaultEncoding;
  }

  public void setDefaultEncoding(String defaultEncoding) {
    this.defaultEncoding = defaultEncoding;
  }

  public Boolean getDebug() {
    return debug;
  }

  public void setDebug(Boolean debug) {
    this.debug = debug;
  }

  public Smtp getSmtp() {
    return smtp;
  }

  public void setSmtp(Smtp smtp) {
    this.smtp = smtp;
  }
}
