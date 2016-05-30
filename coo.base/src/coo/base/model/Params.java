package coo.base.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import coo.base.constants.Chars;
import coo.base.util.CollectionUtils;
import coo.base.util.StringUtils;

/**
 * 参数对象，实现Map与分行的键值对之间的转换，便于存取参数型数据。
 */
@SuppressWarnings("serial")
public class Params extends LinkedHashMap<String, String> {
  /**
   * 获取指定键名的整型值。
   * 
   * @param name 键名
   * @return 返回指定键名的整型值。
   */
  public Integer getInteger(String name) {
    if (StringUtils.isNotBlank(get(name))) {
      return Integer.valueOf(get(name));
    } else {
      return null;
    }
  }

  /**
   * 获取指定键名的布尔型值。
   * 
   * @param name 键名
   * @return 返回指定键名的布尔型值。
   */
  public Boolean getBoolean(String name) {
    if (StringUtils.isNotBlank(get(name))) {
      return Boolean.valueOf(get(name));
    } else {
      return null;
    }
  }

  /**
   * 获取指定键名的列表型值。
   * 
   * @param name 键名
   * @return 返回指定键名的列表型值。
   */
  public List<String> getList(String name) {
    List<String> list = new ArrayList<String>();
    if (StringUtils.isNotBlank(get(name))) {
      list = Arrays.asList(get(name).split(Chars.COMMA));
    }
    return list;
  }

  /**
   * 放入列表。
   * 
   * @param name 键名
   * @param list 列表
   */
  public void putList(String name, List<String> list) {
    String value = "";
    if (!CollectionUtils.isEmpty(list)) {
      value = StringUtils.join(list, Chars.COMMA);
    }
    put(name, value);
  }

  /**
   * 获取指定键名的Map型值。
   * 
   * @param name 键名
   * @return 返回指定键名的Map型值。
   */
  public Map<String, String> getMap(String name) {
    Map<String, String> map = new LinkedHashMap<String, String>();
    if (StringUtils.isNotBlank(get(name))) {
      for (String entry : get(name).split(Chars.COMMA)) {
        String key = StringUtils.substringBefore(entry, Chars.COLON);
        String value = StringUtils.substringAfter(entry, Chars.COLON);
        map.put(key, value);
      }
    }
    return map;
  }

  /**
   * 放入Map。
   * 
   * @param name 键名
   * @param map Map
   */
  public void putMap(String name, Map<String, ?> map) {
    List<String> list = new ArrayList<String>();
    for (Map.Entry<String, ?> entry : map.entrySet()) {
      list.add(entry.getKey() + Chars.COLON + entry.getValue());
    }
    putList(name, list);
  }

  /**
   * 从固定格式的参数字符串加载参数。
   * 
   * @param paramsString 参数字符串
   */
  public void fromString(String paramsString) {
    if (StringUtils.isNotBlank(paramsString)) {
      paramsString = paramsString.replaceAll(Chars.CR, "");
      for (String paramString : paramsString.split(Chars.LF)) {
        if (!StringUtils.isBlank(paramString)) {
          String paramName = StringUtils.substringBefore(paramString, Chars.EQUAL);
          String paramValue = StringUtils.substringAfter(paramString, Chars.EQUAL);
          put(paramName, paramValue);
        }
      }
    }
  }

  @Override
  public String toString() {
    List<String> paramStrings = new ArrayList<String>();
    for (String key : keySet()) {
      String paramString = key + Chars.EQUAL + get(key);
      paramStrings.add(paramString);
    }
    if (!paramStrings.isEmpty()) {
      return StringUtils.join(paramStrings, Chars.CR + Chars.LF);
    } else {
      return "";
    }
  }
}
