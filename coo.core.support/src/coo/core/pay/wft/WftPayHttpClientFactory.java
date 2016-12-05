package coo.core.pay.wft;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * 威富通支付HttpClient工厂。
 */
public class WftPayHttpClientFactory extends AbstractFactoryBean<CloseableHttpClient> {
  @Override
  public Class<?> getObjectType() {
    return HttpClient.class;
  }

  @Override
  protected CloseableHttpClient createInstance() {
    SSLContext sslContext = SSLContexts.createDefault();
    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,
        new String[] {"TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    return HttpClients.custom().setSSLSocketFactory(socketFactory).build();
  }
}
