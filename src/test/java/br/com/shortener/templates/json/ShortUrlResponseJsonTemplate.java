package br.com.shortener.templates.json;

import br.com.shortener.gateways.http.json.response.ShortUrlResponseJson;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ShortUrlResponseJsonTemplate implements TemplateLoader {

  @Override
  public void load() {
    Fixture.of(ShortUrlResponseJson.class)
        .addTemplate(
            Templates.SHORT_URL_RESPONSE_JSON,
            new Rule() {
              {
                add("shortUrl", "http://127.0.0.1/short/123456");
              }
            });
  }
}
