package coo.core.pay.wx;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * 微信支付HttpClient工厂。
 */
public class WxPayHttpClientFactory extends AbstractFactoryBean<HttpClient> {
  private String certFilePath;
  private String certPassword;

  @Override
  public Class<?> getObjectType() {
    return HttpClient.class;
  }

  @Override
  protected HttpClient createInstance() throws Exception {
    try (FileInputStream inputStream = new FileInputStream(new File(certFilePath))) {
      KeyStore keyStore = KeyStore.getInstance("PKCS12");
      keyStore.load(inputStream, certPassword.toCharArray());
      SSLContext sslContext =
          SSLContexts.custom().loadKeyMaterial(keyStore, certPassword.toCharArray()).build();
      SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,
          new String[] {"TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
      return HttpClients.custom().setSSLSocketFactory(socketFactory).build();
    }
  }

  public String getCertFilePath() {
    return certFilePath;
  }

  public void setCertFilePath(String certFilePath) {
    this.certFilePath = certFilePath;
  }

  public String getCertPassword() {
    return certPassword;
  }

  public void setCertPassword(String certPassword) {
    this.certPassword = certPassword;
  }
}
