package coo.core.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import coo.base.exception.UncheckedException;
import coo.core.util.SpringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 模版邮件。
 */
public class TemplateMail extends Mail {
  /** 邮件模版名称 */
  private String templateName;
  /** 邮件模版对象 */
  private Object model;
  /** 邮件模版数据 */
  private Map<String, Object> templateModel = new HashMap<String, Object>();

  /**
   * 覆盖父方法，从FreeMarker模版生成邮件正文。
   * 
   * @return 返回邮件正文。
   */
  @Override
  public String getText() {
    try {
      Configuration configuration = SpringUtils.getBean(Configuration.class);
      Template template = configuration.getTemplate(templateName);
      if (model == null) {
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, templateModel);
      } else {
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
      }
    } catch (Exception e) {
      throw new UncheckedException("解析邮件模版时发生异常。", e);
    }
  }

  /**
   * 设置模版变量。
   * 
   * @param name 变量名称
   * @param value 变量值
   */
  public void setVar(String name, Object value) {
    // 如果已经设置了邮件模版对象，则将邮件模版对象置空。
    if (model != null) {
      model = null;
    }
    templateModel.put(name, value);
  }

  public Object getModel() {
    return model;
  }

  public void setModel(Object model) {
    this.model = model;
  }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }
}
