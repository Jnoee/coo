package coo.core.hibernate.search;

import org.hibernate.HibernateException;
import org.hibernate.search.bridge.StringBridge;

import coo.core.model.IEnum;

/**
 * IEnum枚举类型字段对text属性进行全文索引的桥接器。
 */
public class IEnumTextBridge implements StringBridge {
	@Override
	public String objectToString(Object object) {
		if (object == null) {
			return null;
		}
		try {
			return ((IEnum) object).getText();
		} catch (Exception e) {
			throw new HibernateException("生成枚举" + object.getClass()
					+ "的全文索引时发生异常", e);
		}
	}
}