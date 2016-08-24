package coo.core.pay.wx;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.util.BeanUtils;
import coo.base.util.CryptoUtils;

/**
 * 微信支付工具类。
 */
public class WxPayUtils {
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
    for (String paramName : params.keySet()) {
      builder.append(paramName);
      builder.append("=");
      builder.append(params.get(paramName));
      builder.append("&");
    }
    builder.append("key=" + key);
    return CryptoUtils.md5(builder.toString()).toUpperCase();
  }

  /**
   * 生成参数Map。
   * 
   * @param data 数据对象
   * @return 返回参数Map。
   */
  private static Map<String, String> genMap(Object data) {
    List<Field> fields = BeanUtils.getDeclaredFields(data.getClass());
    Map<String, String> params = new TreeMap<>();
    for (Field field : fields) {
      XStreamAlias alias = field.getAnnotation(XStreamAlias.class);
      Object value = BeanUtils.getField(data, field);
      if (value != null) {
        params.put(alias.value(), value.toString());
      }
    }
    return params;
  }
}
