package coo.core.pay.wft;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 预下单响应。
 */
public class PayReply extends WftPayReply {
  @XStreamAlias("services")
  private String services;
  @XStreamAlias("token_id")
  private String tokenId;

  public String getServices() {
    return services;
  }

  public void setServices(String services) {
    this.services = services;
  }

  public String getTokenId() {
    return tokenId;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }
}
