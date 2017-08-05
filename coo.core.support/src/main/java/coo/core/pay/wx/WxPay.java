package coo.core.pay.wx;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;
import coo.base.util.BeanUtils;
import coo.base.util.CryptoUtils;

/**
 * 微信支付组件。
 */
public class WxPay {
  private final Logger log = LoggerFactory.getLogger(getClass());
  private WxPayConfig config;
  private CloseableHttpClient httpClient;
  private RequestConfig requestConfig;

  /**
   * 申请支付。
   * 
   * @param data 业务数据
   * @return 返回响应对象。
   */
  public PayQuery applyPay(PayData data) {
    UnifiedOrderQuery query = new UnifiedOrderQuery(data);
    UnifiedOrderReply reply = new UnifiedOrderReply();
    execute(query, reply);
    return reply.genPayQuery(config.getKey());
  }

  /**
   * 申请退款。
   * 
   * @param data 业务数据
   * @return 返回响应对象。
   */
  public RefundReply refund(RefundData data) {
    RefundQuery query = new RefundQuery(data);
    RefundReply reply = new RefundReply();
    execute(query, reply);
    return reply;
  }

  /**
   * 获取支付通知数据。
   * 
   * @param request 微信支付通知的HTTP请求
   * @return 返回支付通知数据。
   */
  public PayNotifyData getPayNotifyData(HttpServletRequest request) {
    PayNotify notify = new PayNotify(request, config.getKey());
    return notify.getData();
  }

  /**
   * 获取支付通知数据。
   * 
   * @param xml 微信支付通知XML内容
   * @return 返回支付通知数据。
   */
  public PayNotifyData getPayNotifyData(String xml) {
    PayNotify notify = new PayNotify(xml, config.getKey());
    return notify.getData();
  }

  /**
   * 调用微信支付接口。
   * 
   * @param query 请求对象
   * @param reply 响应对象
   */
  private void execute(WxPayQuery query, WxPayReply reply) {
    String url = config.getApiUrl() + query.getService();
    processQuery(query);
    String queryXml = query.genXml(config.getKey());
    String replyXml = "";
    try {
      log.debug(queryXml);
      HttpPost httpPost = new HttpPost(url);
      httpPost.addHeader("Content-Type", "text/xml");
      httpPost.setConfig(requestConfig);

      StringEntity queryEntity = new StringEntity(queryXml, Encoding.UTF_8);
      httpPost.setEntity(queryEntity);

      HttpEntity replyEntity = httpClient.execute(httpPost).getEntity();
      replyXml = EntityUtils.toString(replyEntity, Encoding.UTF_8);
      reply = WxPayUtils.parseXml(replyXml, config.getKey(), reply.getClass());
      log.debug(replyXml);
    } catch (Exception e) {
      logError(url, queryXml, replyXml, e);
      throw new UncheckedException("调用微信支付接口发生异常。", e);
    }
  }

  /**
   * 处理请求对象，自动填充统一配置参数。
   * 
   * @param query 请求对象
   */
  private void processQuery(WxPayQuery query) {
    query.appId = config.getAppId();
    query.mchId = config.getMchId();
    query.nonceStr = CryptoUtils.genRandomCode(CryptoUtils.ALL, 16);
    Field notifyUrlField = BeanUtils.findField(query.getClass(), "notifyUrl");
    if (notifyUrlField != null) {
      BeanUtils.setField(query, notifyUrlField, config.getNotifyUrl());
    }
    Field opUserIdField = BeanUtils.findField(query.getClass(), "opUserId");
    if (opUserIdField != null) {
      Object opUserId = BeanUtils.getField(query, opUserIdField);
      if (opUserId == null) {
        BeanUtils.setField(query, opUserIdField, config.getMchId());
      }
    }
  }

  /**
   * 记录异常日志。
   * 
   * @param url 调用地址
   * @param queryXml 请求XML
   * @param replyXml 响应XML
   * @param e 异常
   */
  private void logError(String url, String queryXml, String replyXml, Exception e) {
    String msg = String.format("[微信支付调用失败：%s]%n[请求消息：%s]%n[响应消息：%s]%n[异常信息：%s]", url, queryXml,
        replyXml, e.getMessage());
    log.error(msg);
  }

  public WxPayConfig getConfig() {
    return config;
  }

  public void setConfig(WxPayConfig config) {
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
