package coo.core.mail;

import org.springframework.stereotype.Component;

import coo.core.template.AbstractTemplateSettings;

@Component("coo.core.mail.TemplateSettings")
public class TemplateSettings extends AbstractTemplateSettings {
  public TemplateSettings() {
    addTemplatePath("classpath:/coo/core/mail");
  }
}
