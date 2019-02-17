package br.com.shortener.templates.json;

import br.com.shortener.gateways.http.json.response.ShortUrlRequestResponseJson;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalDateTime;

public class ShortUrlRequestResponseJsonTemplate implements TemplateLoader {

  @Override
  public void load() {
    Fixture.of(ShortUrlRequestResponseJson.class)
        .addTemplate(
            Templates.SHORT_URL_REQUEST_RESPONSE_JSON,
            new Rule() {
              {
                add("requestIp", "127.0.0.1");
                add("requestDateTime", LocalDateTime.now());
              }
            });
  }
}
