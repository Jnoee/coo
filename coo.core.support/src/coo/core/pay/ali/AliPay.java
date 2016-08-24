package coo.core.pay.ali;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 支付宝支付组件。
 */
public class AliPay {
  private AliPayConfig config;
  private CloseableHttpClient httpClient;
  private RequestConfig requestConfig;

  public AliPayConfig getConfig() {
    return config;
  }

  public void setConfig(AliPayConfig config) {
    this.config = config;
  }

  public CloseableHttpClient getHttpClient() {
    return httpClient;
  }

  public void setHttpClient(CloseableHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public RequestConfig getRequestConfig() {
    return requestConfig;
  }

  public void setRequestConfig(RequestConfig requestConfig) {
    this.requestConfig = requestConfig;
  }
}
