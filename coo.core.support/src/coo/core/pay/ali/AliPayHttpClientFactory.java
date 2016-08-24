package coo.core.pay.ali;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * 支付宝支付HttpClient工厂。
 */
public class AliPayHttpClientFactory extends AbstractFactoryBean<HttpClient> {
  @Override
  public Class<?> getObjectType() {
    return HttpClient.class;
  }

  @Override
  protected HttpClient createInstance() throws Exception {
    SSLContext sslContext = SSLContexts.createDefault();
    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,
        new String[] {"TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    return HttpClients.custom().setSSLSocketFactory(socketFactory).build();
  }
}
