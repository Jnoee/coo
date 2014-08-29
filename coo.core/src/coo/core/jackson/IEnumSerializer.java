package coo.core.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import coo.core.model.IEnum;

/**
 * IEnum枚举转换器。
 */
public class IEnumSerializer extends JsonSerializer<IEnum> {
	@Override
	public Class<IEnum> handledType() {
		return IEnum.class;
	}

	@Override
	public void serialize(IEnum value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException {
		jgen.writeString(value.getValue());
	}
}