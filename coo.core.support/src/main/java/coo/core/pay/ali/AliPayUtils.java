package coo.core.pay.ali;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import coo.base.constants.Algorithm;
import coo.base.constants.Encoding;
import coo.base.exception.BusinessException;
import coo.base.exception.UncheckedException;
import coo.base.util.BeanUtils;
import coo.base.util.CryptoUtils;
import coo.base.util.StringUtils;

/**
 * 支付宝支付工具类。
 */
public class AliPayUtils {
  public static <T extends AliPayReply> T parseXml(String xml, Class<T> clazz) {
    try {
      XmlMapper xmlMapper = new XmlMapper();
      T reply = xmlMapper.readValue(xml, clazz);
      if ("F".equals(reply.getIsSuccess())) {
        throw new BusinessException("支付宝支付调用失败：" + reply.getError());
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
   * @param privateKey 商家私钥
   * @return 返回签名。
   */
  public static String sign(Object data, String privateKey) {
    PrivateKey key = CryptoUtils.getPrivateKey(privateKey);
    Map<String, String> dataMap = genMap(data);
    String content = genSignContent(dataMap);
    String sign = CryptoUtils.sign(content, Algorithm.SHA1RSA, key);
    try {
      return URLEncoder.encode(sign, Encoding.UTF_8);
    } catch (UnsupportedEncodingException e) {
      throw new UncheckedException(e);
    }
  }

  /**
   * 验证签名。
   * 
   * @param data 数据对象
   * @param publicKey 支付宝公钥
   * @return 返回验证签名是否成功。
   */
  public static Boolean verify(Object data, String publicKey) {
    PublicKey key = CryptoUtils.getPublicKey(publicKey);
    Map<String, String> dataMap = genMap(data);
    String content = genSignContent(dataMap);
    String sign = dataMap.get("sign");
    return CryptoUtils.verify(content, Algorithm.SHA1RSA, key, sign);
  }

  /**
   * 生成参数字符串。
   * 
   * @param data 数据对象
   * @return 返回参数字符串。
   */
  public static String genParams(Object data) {
    Map<String, String> dataMap = genMap(data);
    List<String> datas = new ArrayList<>();
    for (Map.Entry<String, String> entry : dataMap.entrySet()) {
      datas.add(entry.getKey() + "=" + entry.getValue());
    }
    return StringUtils.join(datas, "&");
  }

  /**
   * 从HTTP请求中获取订单号。
   * 
   * @param request HTTP请求
   * @return 返回订单号。
   */
  public static String getOrderBn(HttpServletRequest request) {
    return request.getParameter("out_trade_no");
  }

  /**
   * 将HTTP请求参数转换成Map。
   * 
   * @param request HTTP请求
   * @return 返回参数Map。
   */
  public static Map<String, String> genMap(HttpServletRequest request) {
    Map<String, String[]> paramsMap = request.getParameterMap();
    Map<String, String> resultMap = new HashMap<>();
    for (Entry<String, String[]> param : paramsMap.entrySet()) {
      resultMap.put(param.getKey(), StringUtils.join(param.getValue(), ","));
    }
    return resultMap;
  }

  /**
   * 填充数据对象。
   * 
   * @param dataMap 数据Map
   * @param data 数据对象
   */
  public static void fillData(Map<String, String> dataMap, Object data) {
    List<Field> fields = BeanUtils.findField(data.getClass(), JacksonXmlProperty.class);
    for (Field field : fields) {
      if (String.class.isAssignableFrom(field.getType())) {
        JacksonXmlProperty alias = field.getAnnotation(JacksonXmlProperty.class);
        Object value = dataMap.get(alias.localName());
        if (value != null) {
          BeanUtils.setField(data, field, value);
        }
      }
    }
  }

  /**
   * 生成签名内容。
   * 
   * @param dataMap 数据Map
   * @return 返回签名内容。
   */
  private static String genSignContent(Map<String, String> dataMap) {
    List<String> datas = new ArrayList<>();
    for (Map.Entry<String, String> entry : dataMap.entrySet()) {
      if (!entry.getKey().equals("sign") && !entry.getKey().equals("sign_type")) {
        datas.add(entry.getKey() + "=" + entry.getValue());
      }
    }
    return StringUtils.join(datas, "&");
  }

  /**
   * 生成数据Map。
   * 
   * @param data 数据对象
   * @return 返回数据Map。
   */
  private static Map<String, String> genMap(Object data) {
    Map<String, String> dataMap = new TreeMap<>();
    List<Field> fields = BeanUtils.findField(data.getClass(), JacksonXmlProperty.class);
    for (Field field : fields) {
      JacksonXmlProperty alias = field.getAnnotation(JacksonXmlProperty.class);
      Object value = BeanUtils.getField(data, field);
      if (value != null && StringUtils.isNotBlank(value.toString())) {
        dataMap.put(alias.localName(), value.toString());
      }
    }
    return dataMap;
  }

  /**
   * 私有构造方法。
   */
  private AliPayUtils() {}
}
