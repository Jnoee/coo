package coo.core.pay.wft;

import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * 威富通支付RequestConfig工厂。
 */
public class WftPayRequestConfigFactory extends AbstractFactoryBean<RequestConfig> {
  private Integer connectTimeout = 5000;
  private Integer socketTimeout = 5000;
  private Integer connectionRequestTimeout = 10000;

  @Override
  public Class<?> getObjectType() {
    return RequestConfig.class;
  }

  @Override
  protected RequestConfig createInstance() throws Exception {
    return RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout)
        .setConnectionRequestTimeout(connectionRequestTimeout).build();
  }

  public Integer getConnectTimeout() {
    return connectTimeout;
  }

  public void setConnectTimeout(Integer connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public Integer getSocketTimeout() {
    return socketTimeout;
  }

  public void setSocketTimeout(Integer socketTimeout) {
    this.socketTimeout = socketTimeout;
  }

  public Integer getConnectionRequestTimeout() {
    return connectionRequestTimeout;
  }

  public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
    this.connectionRequestTimeout = connectionRequestTimeout;
  }
}
