package coo.core.sms.esms;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coo.base.constants.Encoding;
import coo.base.util.CollectionUtils;
import coo.base.util.CryptoUtils;
import coo.base.util.DateUtils;
import coo.base.util.StringUtils;
import coo.core.sms.SmsPayload;
import coo.core.sms.SmsSchedulePayload;
import coo.core.sms.SmsSender;

/**
 * Esms短信组件。
 */
public class EsmsSender implements SmsSender {
  private final Logger log = LoggerFactory.getLogger(getClass());
  private EsmsConfig config;

  /**
   * 构造方法。
   * 
   * @param config 短信配置
   */
  public EsmsSender(EsmsConfig config) {
    this.config = config;
  }

  @Override
  public void send(SmsPayload payload) {
    if (CollectionUtils.isEmpty(payload.getMobiles())) {
      log.warn("发送短信手机号码列表为空，未发送短信：{}", payload.getContent());
      return;
    }
    List<NameValuePair> params = genPubParams(payload);
    params.add(new BasicNameValuePair("cmd", "send"));
    execute(params);
  }

  @Override
  public void schedule(SmsSchedulePayload payload) {
    if (CollectionUtils.isEmpty(payload.getMobiles())) {
      log.warn("发送短信手机号码列表为空，未发送短信：{}", payload.getContent());
      return;
    }
    List<NameValuePair> params = genPubParams(payload);
    params.add(new BasicNameValuePair("cmd", "tsend"));
    params.add(new BasicNameValuePair("senddate", DateUtils.format(payload.getSendTime())));
    params.add(
        new BasicNameValuePair("sendtime", DateUtils.format(payload.getSendTime(), "HH:mm:ss")));
    execute(params);
  }

  /**
   * 执行发送短信。
   * 
   * @param params 参数
   */
  private void execute(List<NameValuePair> params) {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    try {
      String url = config.getUrl() + URLEncodedUtils.format(params, Encoding.GBK);
      log.debug("url:" + url);
      HttpGet httpPost = new HttpGet(url);
      httpPost.setConfig(getRequestConfig());

      HttpEntity replyEntity = httpclient.execute(httpPost).getEntity();
      String replyString = EntityUtils.toString(replyEntity);
      if ("100".equals(replyString)) {
        log.debug("调用短信接口成功，返回结果：{}", replyString);
      } else {
        log.warn("调用短信接口失败，返回结果：{}", replyString);
      }
    } catch (Exception e) {
      log.error("调用短信接口发生异常。", e);
    }
  }

  /**
   * 生成公共参数。
   * 
   * @param payload 短信载体
   * @return 返回公共参数。
   */
  private List<NameValuePair> genPubParams(SmsPayload payload) {
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("uid", config.getUsername()));
    params.add(new BasicNameValuePair("psw", CryptoUtils.md5(config.getPassword())));
    params.add(new BasicNameValuePair("mobiles", StringUtils.join(payload.getMobiles(), ",")));
    params.add(new BasicNameValuePair("msg", payload.getTag() + payload.getContent()));
    params.add(new BasicNameValuePair("msgid", genMsgId()));
    return params;
  }

  /**
   * 生成消息ID。
   * 
   * @return 返回消息ID。
   */
  private String genMsgId() {
    return System.currentTimeMillis() + CryptoUtils.genRandomCode(CryptoUtils.NUMBER, 4);
  }

  /**
   * 获取请求配置。
   * 
   * @return 返回请求配置。
   */
  private RequestConfig getRequestConfig() {
    return RequestConfig.custom().setSocketTimeout(10 * 1000).setConnectTimeout(10 * 1000).build();
  }
}
