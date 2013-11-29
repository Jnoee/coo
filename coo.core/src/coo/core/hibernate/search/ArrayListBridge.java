package coo.core.hibernate.search;

import java.util.List;

import org.hibernate.search.bridge.StringBridge;

import coo.base.util.CollectionUtils;
import coo.base.util.StringUtils;

/**
 * 字符串列表字段全文索引桥接器。
 */
public class ArrayListBridge implements StringBridge {
	@Override
	public String objectToString(Object object) {
		@SuppressWarnings("unchecked")
		List<String> strs = (List<String>) object;
		if (CollectionUtils.isEmpty(strs)) {
			return "";
		}
		return StringUtils.join(strs, " ");
	}
}