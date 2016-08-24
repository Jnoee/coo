package coo.core.pay.wx;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import coo.base.constants.Encoding;

/**
 * 微信支付交互数据基类。
 */
public abstract class WxPayData {
  private static Map<Class<?>, XStream> XSTREAMS = new HashMap<>();
  /** 应用ID */
  @XStreamAlias("appid")
  protected String appId;
  /** 商户号 */
  @XStreamAlias("mch_id")
  protected String mchId;
  /** 随机字符串 */
  @XStreamAlias("nonce_str")
  protected String nonceStr;
  /** 签名 */
  @XStreamAlias("sign")
  protected String sign;

  /**
   * 获取XStream解析器。
   * 
   * @return 返回XStream解析器。
   */
  protected XStream getXstream() {
    XStream xstream = XSTREAMS.get(getClass());
    if (xstream == null) {
      xstream = new XStream(new DomDriver(Encoding.UTF_8, new XmlFriendlyNameCoder("-_", "_")));
      xstream.autodetectAnnotations(true);
      xstream.alias("xml", getClass());
      XSTREAMS.put(getClass(), xstream);
    }
    return xstream;
  }

  protected String getAppId() {
    return appId;
  }

  protected void setAppId(String appId) {
    this.appId = appId;
  }

  protected String getMchId() {
    return mchId;
  }

  protected void setMchId(String mchId) {
    this.mchId = mchId;
  }

  protected String getNonceStr() {
    return nonceStr;
  }

  protected void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  protected String getSign() {
    return sign;
  }

  protected void setSign(String sign) {
    this.sign = sign;
  }
}
