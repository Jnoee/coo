package coo.core.hibernate.search;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.search.bridge.StringBridge;

import coo.base.util.CollectionUtils;
import coo.base.util.StringUtils;
import coo.core.model.UuidEntity;

/**
 * UuidEntity列表字段属性全文索引桥接器。
 */
public class UuidEntityListBridge implements StringBridge {
	@Override
	public String objectToString(Object object) {
		@SuppressWarnings("unchecked")
		List<UuidEntity> entities = (ArrayList<UuidEntity>) object;
		if (CollectionUtils.isEmpty(entities)) {
			return "";
		}
		List<String> enumValues = new ArrayList<String>();
		for (UuidEntity entity : entities) {
			enumValues.add(entity.getId());
		}
		return StringUtils.join(enumValues, " ");
	}
}