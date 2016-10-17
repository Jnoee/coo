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
import java.util.TreeMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import coo.base.constants.Algorithm;
import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;
import coo.base.util.BeanUtils;
import coo.base.util.CryptoUtils;
import coo.base.util.StringUtils;

/**
 * 支付宝支付工具类。
 */
public class AliPayUtils {
  private static Map<Class<?>, XStream> XSTREAMS = new HashMap<>();

  /**
   * 获取指定数据类的XStream工具。
   * 
   * @param dataClass 数据类
   * @return 返回指定数据类的XStream工具。
   */
  public static XStream getXstream(Class<?> dataClass) {
    XStream xstream = XSTREAMS.get(dataClass);
    if (xstream == null) {
      xstream = new XStream(new DomDriver(Encoding.UTF_8, new XmlFriendlyNameCoder("-_", "_")));
      xstream.autodetectAnnotations(true);
      xstream.alias("alipay", dataClass);
      XSTREAMS.put(dataClass, xstream);
    }
    return xstream;
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
    for (String key : dataMap.keySet()) {
      datas.add(key + "=" + dataMap.get(key));
    }
    return StringUtils.join(datas, "&");
  }

  /**
   * 填充数据对象。
   * 
   * @param dataMap 数据Map
   * @param data 数据对象
   */
  public static void fillData(Map<String, String> dataMap, Object data) {
    List<Field> fields = BeanUtils.findField(data.getClass(), XStreamAlias.class);
    for (Field field : fields) {
      if (String.class.isAssignableFrom(field.getType())) {
        XStreamAlias alias = field.getAnnotation(XStreamAlias.class);
        Object value = dataMap.get(alias.value());
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
    for (String key : dataMap.keySet()) {
      if (!key.equals("sign") && !key.equals("sign_type")) {
        datas.add(key + "=" + dataMap.get(key));
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
    List<Field> fields = BeanUtils.findField(data.getClass(), XStreamAlias.class);
    for (Field field : fields) {
      XStreamAlias alias = field.getAnnotation(XStreamAlias.class);
      Object value = BeanUtils.getField(data, field);
      if (value != null && StringUtils.isNotBlank(value.toString())) {
        dataMap.put(alias.value(), value.toString());
      }
    }
    return dataMap;
  }
}
