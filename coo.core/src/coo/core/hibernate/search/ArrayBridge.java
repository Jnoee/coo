package coo.core.hibernate.search;

import org.hibernate.search.bridge.StringBridge;

import coo.base.util.CollectionUtils;
import coo.base.util.StringUtils;

/**
 * 字符串数组字段全文索引桥接器。
 */
public class ArrayBridge implements StringBridge {
	@Override
	public String objectToString(Object object) {
		String[] strs = (String[]) object;
		if (CollectionUtils.isEmpty(strs)) {
			return "";
		}
		return StringUtils.join(strs, " ");
	}
}
