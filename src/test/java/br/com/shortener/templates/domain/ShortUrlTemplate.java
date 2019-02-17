package br.com.shortener.templates.domain;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ShortUrlTemplate implements TemplateLoader {

  @Override
  public void load() {
    Fixture.of(ShortUrl.class)
        .addTemplate(
            Templates.SHORT_URL,
            new Rule() {
              {
                add("id", "5c685e51196c620f5a860fa9");
                add("url", "https://www.google.com.br");
              }
            });
  }
}
