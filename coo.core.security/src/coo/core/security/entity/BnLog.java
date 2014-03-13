package coo.core.security.entity;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotEmpty;

import coo.base.util.BeanUtils;
import coo.base.util.DateUtils;
import coo.base.util.StringUtils;
import coo.core.hibernate.search.DateBridge;
import coo.core.model.UuidEntity;
import coo.core.security.annotations.Log;
import coo.core.security.model.LogData;

/**
 * 业务日志。
 */
@Entity
@Table(name = "Syst_BnLog")
@Indexed
public class BnLog extends UuidEntity {
	/** 创建人 */
	@NotEmpty
	@Field(analyze = Analyze.NO)
	private String creator;
	/** 创建时间 */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Field(analyze = Analyze.NO)
	@FieldBridge(impl = DateBridge.class)
	private Date createDate;
	/** 日志信息 */
	@NotEmpty
	@Field
	private String message;
	/** 原数据 */
	@Field
	private String origData;
	/** 新数据 */
	@Field
	private String newData;

	/**
	 * 构造方法。
	 */
	public BnLog() {
	}

	/**
	 * 构造方法。
	 * 
	 * @param message
	 *            日志信息
	 */
	public BnLog(String message) {
		this.message = message;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOrigData() {
		return origData;
	}

	public void setOrigData(String origDate) {
		this.origData = origDate;
	}

	public void setOrigData(Object origDataObject) {
		this.origData = getXml(origDataObject);
	}

	public String getNewData() {
		return newData;
	}

	public void setNewData(String newDate) {
		this.newData = newDate;
	}

	public void setNewData(Object newDataObject) {
		this.newData = getXml(newDataObject);
	}

	/**
	 * 判断是否记录了业务数据。
	 * 
	 * @return 如果记录了业务数据返回true，否则返回false。
	 */
	public Boolean hasData() {
		return StringUtils.isNotEmpty(origData)
				|| StringUtils.isNotEmpty(newData);
	}

	/**
	 * 输出日志数据项。用于原数据与新数据比较。
	 * 
	 * @return 返回日志数据项列表。
	 */
	public List<LogData> toLogData() {
		List<LogData> datas = new ArrayList<LogData>();
		fillInOrigData(datas);
		fillInNewData(datas);
		return datas;
	}

	/**
	 * 填充源数据列表。
	 * 
	 * @param datas
	 *            数据列表
	 */
	@SuppressWarnings("unchecked")
	private void fillInOrigData(List<LogData> datas) {
		try {
			SAXReader reader = new SAXReader();
			if (StringUtils.isNotEmpty(origData)) {
				Document xml = reader.read(new StringReader(origData));
				List<Element> elements = xml.getRootElement().elements();
				if (datas.isEmpty()) {
					for (Element element : elements) {
						LogData data = new LogData();
						data.setText(element.elementText("text"));
						data.setOrigData(element.elementText("value"));
						datas.add(data);
					}
				} else {
					for (int i = 0; i < datas.size(); i++) {
						datas.get(i).setOrigData(
								elements.get(i).elementText("value"));
					}
				}
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 填充新数据列表。
	 * 
	 * @param datas
	 *            新数据列表。
	 */
	@SuppressWarnings("unchecked")
	private void fillInNewData(List<LogData> datas) {
		try {
			SAXReader reader = new SAXReader();
			if (StringUtils.isNotEmpty(newData)) {
				Document xml = reader.read(new StringReader(newData));
				List<Element> elements = xml.getRootElement().elements();
				if (datas.isEmpty()) {
					for (Element element : elements) {
						LogData data = new LogData();
						data.setText(element.elementText("text"));
						data.setNewData(element.elementText("value"));
						datas.add(data);
					}
				} else {
					for (int i = 0; i < datas.size(); i++) {
						datas.get(i).setNewData(
								elements.get(i).elementText("value"));
					}
				}
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据数据对象中的@Log注解生成数据对象的日志xml。
	 * 
	 * @param data
	 *            数据对象
	 * @return 返回日志xml字符串。
	 */
	private String getXml(Object data) {
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("<" + data.getClass().getSimpleName() + ">");

		List<java.lang.reflect.Field> fields = BeanUtils.findField(
				data.getClass(), Log.class);
		for (java.lang.reflect.Field field : fields) {
			Log log = field.getAnnotation(Log.class);
			xml.append("<" + field.getName() + ">");
			xml.append("<text>" + log.text() + "</text>");

			String value = "";
			Object fieldValue = BeanUtils.getField(data, field);
			if (fieldValue != null) {
				if (fieldValue.getClass().isPrimitive()) {
					value = getPrimitiveFieldValue(fieldValue, log);
				} else {
					value = getBeanFieldValue(fieldValue, log);
				}
			}
			xml.append("<value>" + value + "</value>");
			xml.append("</" + field.getName() + ">");
		}

		xml.append("</" + data.getClass().getSimpleName() + ">");
		return xml.toString();
	}

	/**
	 * 获取基础类型属性值对象的日志值。
	 * 
	 * @param fieldValue
	 *            属性值对象
	 * @param log
	 *            日志注解
	 * @return 返回基础类型属性值对象的日志值。
	 */
	private String getPrimitiveFieldValue(Object fieldValue, Log log) {
		String value = fieldValue.toString();
		if (fieldValue instanceof Date) {
			value = DateUtils.format((Date) fieldValue, log.format());
		}
		return value;
	}

	/**
	 * 获取Bean类型属性值对象的日志值。
	 * 
	 * @param fieldValue
	 *            属性值对象
	 * @param log
	 *            日志注解
	 * @return 返回Bean类型属性值对象的日志值。
	 */
	private String getBeanFieldValue(Object fieldValue, Log log) {
		// 如果属性值对象是Hibernate懒加载对象，需要把它还原为实际类型的对象。
		if (fieldValue instanceof HibernateProxy) {
			fieldValue = ((HibernateProxy) fieldValue)
					.getHibernateLazyInitializer().getImplementation();
		}
		if (StringUtils.isNotEmpty(log.property())) {
			fieldValue = BeanUtils.getField(fieldValue, log.property());
		}
		return getPrimitiveFieldValue(fieldValue, log);
	}
}
