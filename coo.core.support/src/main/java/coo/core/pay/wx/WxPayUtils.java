package coo.core.pay.wx;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import coo.base.constants.Encoding;
import coo.base.exception.BusinessException;
import coo.base.exception.UncheckedException;
import coo.base.util.BeanUtils;
import coo.base.util.CryptoUtils;
import coo.base.util.FileUtils;
import coo.base.util.StringUtils;

/**
 * 微信支付工具类。
 */
public class WxPayUtils {
  public static <T extends WxPayReply> T parseXml(String xml, String key, Class<T> clazz) {
    try {
      XmlMapper xmlMapper = new XmlMapper();
      T reply = xmlMapper.readValue(xml, clazz);
      if (!reply.getSign().equals(WxPayUtils.sign(reply, key))) {
        throw new BusinessException("微信支付调用失败：验证签名失败，返回数据为[" + xml + "]");
      }
      if ("FAIL".equals(reply.getReturnCode())) {
        throw new BusinessException("微信支付调用失败：" + reply.getReturnMsg());
      }
      if ("FAIL".equals(reply.getResultCode())) {
        throw new BusinessException("微信支付交易失败：" + reply.getErrCode() + "-" + reply.getErrCodeDes());
      }
      return reply;
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      throw new UncheckedException("解析XML时发生异常。", e);
    }
  }

  /**
   * 签名。
   * 
   * @param data 数据对象
   * @param key 密钥
   * @return 返回签名。
   */
  public static String sign(Object data, String key) {
    StringBuilder builder = new StringBuilder();
    Map<String, String> params = genMap(data);
    params.remove("sign");
    for (Map.Entry<String, String> entry : params.entrySet()) {
      builder.append(entry.getKey());
      builder.append("=");
      builder.append(entry.getValue());
      builder.append("&");
    }
    builder.append("key=" + key);
    return CryptoUtils.md5(builder.toString()).toUpperCase();
  }

  /**
   * 从XML内容中获取订单号。
   * 
   * @param xml XML内容
   * @return 返回订单号。
   */
  public static String getOrderBn(String xml) {
    return StringUtils.substringBetween(xml, "<out_trade_no><![CDATA[", "]]></out_trade_no>");
  }

  /**
   * 从HTTP请求中获取XML字符串。
   * 
   * @param request HTTP请求
   * @return 返回XML字符串。
   */
  public static String genXml(HttpServletRequest request) {
    try (InputStream xmlIn = request.getInputStream()) {
      byte[] xmlBytes = FileUtils.toByteArray(xmlIn);
      return new String(xmlBytes, Encoding.UTF_8);
    } catch (IOException e) {
      throw new UncheckedException(e);
    }
  }

  /**
   * 生成参数Map。
   * 
   * @param data 数据对象
   * @return 返回参数Map。
   */
  private static Map<String, String> genMap(Object data) {
    Map<String, String> params = new TreeMap<>();
    List<Field> fields = BeanUtils.findField(data.getClass(), JacksonXmlProperty.class);
    for (Field field : fields) {
      JacksonXmlProperty alias = field.getAnnotation(JacksonXmlProperty.class);
      Object value = BeanUtils.getField(data, field);
      if (value != null && StringUtils.isNotBlank(value.toString())) {
        params.put(alias.localName(), value.toString());
      }
    }
    return params;
  }

  /**
   * 私有构造方法。
   */
  private WxPayUtils() {}
}
