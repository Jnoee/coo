package coo.core.pay.ali;

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

/**
 * 支付宝支付组件。
 */
public class AliPay {
  private final Logger log = LoggerFactory.getLogger(getClass());
  private AliPayConfig config;
  private CloseableHttpClient httpClient;
  private RequestConfig requestConfig;

  /**
   * 申请支付。
   * 
   * @param data 申请支付业务数据
   * @return 返回申请支付请求。
   */
  public PayQuery applyPay(PayData data) {
    PayQuery query = new PayQuery(data);
    processQuery(query);
    return query;
  }

  /**
   * 无密退款。
   * 
   * @param data 无密退款业务数据
   * @return 返回无密退款响应。
   */
  public NoPwdRefundReply refund(NoPwdRefundData data) {
    NoPwdRefundQuery query = new NoPwdRefundQuery(data);
    processQuery(query);
    NoPwdRefundReply reply = new NoPwdRefundReply();
    execute(query, reply);
    return reply;
  }

  /**
   * 获取交易通知数据。
   * 
   * @param request 支付宝交易通知的HTTP请求
   * @return 返回交易通知数据。
   */
  public TradeNotifyData getTradeNotifyData(HttpServletRequest request) {
    TradeNotify notify = new TradeNotify(request, config.getPublicKey());
    return notify.getData();
  }

  /**
   * 调用支付宝支付接口。
   * 
   * @param query 请求对象
   * @param reply 响应对象
   */
  private void execute(AliPayQuery query, AliPayReply reply) {
    String url = config.getApiUrl();
    processQuery(query);
    String queryParams = query.genParams();
    String replyXml = "";
    try {
      log.debug(queryParams);
      HttpPost httpPost = new HttpPost(url);
      httpPost.setConfig(requestConfig);

      StringEntity queryEntity = new StringEntity(queryParams, Encoding.UTF_8);
      httpPost.setEntity(queryEntity);
      httpPost.addHeader("Content-Type",
          "application/x-www-form-urlencoded; text/html; charset=" + Encoding.UTF_8);

      HttpEntity replyEntity = httpClient.execute(httpPost).getEntity();
      replyXml = EntityUtils.toString(replyEntity, Encoding.UTF_8);
      reply = AliPayUtils.parseXml(replyXml, reply.getClass());
      log.debug(replyXml);
    } catch (Exception e) {
      logError(url, queryParams, replyXml, e);
      throw new UncheckedException("调用支付宝支付接口发生异常。", e);
    }
  }

  /**
   * 处理请求对象，自动填充统一配置参数。
   * 
   * @param query 请求对象
   */
  private void processQuery(AliPayQuery query) {
    query.partner = config.getPartner();
    Field notifyUrlField = BeanUtils.findField(query.getClass(), "notifyUrl");
    if (notifyUrlField != null) {
      BeanUtils.setField(query, notifyUrlField, config.getNotifyUrl());
    }
    Field sellerIdField = BeanUtils.findField(query.getClass(), "sellerId");
    if (sellerIdField != null) {
      BeanUtils.setField(query, sellerIdField, config.getPartner());
    }
    query.setSign(AliPayUtils.sign(query, config.getPrivateKey()));
  }

  /**
   * 记录异常日志。
   * 
   * @param url 调用地址
   * @param queryParams 请求参数
   * @param replyXml 响应XML
   * @param e 异常
   */
  private void logError(String url, String queryParams, String replyXml, Exception e) {
    String msg = String.format("[支付宝支付调用失败：%s]%n[请求消息：%s]%n[响应消息：%s]%n[异常信息：%s]", url, queryParams,
        replyXml, e.getMessage());
    log.error(msg);
  }

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
