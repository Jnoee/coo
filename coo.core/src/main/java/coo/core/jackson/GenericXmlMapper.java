package coo.core.jackson;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import coo.base.util.DateUtils;

public class GenericXmlMapper extends XmlMapper {
  private static final long serialVersionUID = -1172780298906652649L;

  public GenericXmlMapper() {
    setSerializationInclusion(Include.NON_NULL);
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    setDateFormat(new SimpleDateFormat(DateUtils.SECOND));
    Hibernate5Module hibernateModule = new Hibernate5Module();
    hibernateModule.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
    registerModule(hibernateModule);
    registerModule(new JodaModule());
    registerModule(new JaxbAnnotationModule());
  }
}
