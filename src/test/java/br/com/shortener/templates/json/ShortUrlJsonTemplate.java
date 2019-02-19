package br.com.shortener.templates.json;

import br.com.shortener.gateways.http.json.response.ShortUrlJson;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ShortUrlJsonTemplate implements TemplateLoader {

  @Override
  public void load() {
    Fixture.of(ShortUrlJson.class)
        .addTemplate(
            Templates.SHORT_URL_JSON,
            new Rule() {
              {
                add("url", "https://www.google.com.br");
              }
            });
  }
}
