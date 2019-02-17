package br.com.shortener.templates.json;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.http.json.response.ShortUrlRequestResponseJson;
import br.com.shortener.gateways.http.json.response.ShortUrlStatisticsResponseJson;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ShortUrlStatisticsResponseJsonTemplate implements TemplateLoader {

  @Override
  public void load() {
    Fixture.of(ShortUrlStatisticsResponseJson.class)
        .addTemplate(
            Templates.SHORT_URL_STATISTICS_RESPONSE_JSON,
            new Rule() {
              {
                add("shortUrl", one(ShortUrl.class, Templates.SHORT_URL));
                add("numberOfRequests", 10l);
                add(
                    "lastRequest",
                    one(
                        ShortUrlRequestResponseJson.class,
                        Templates.SHORT_URL_REQUEST_RESPONSE_JSON));
                add(
                    "lastRequests",
                    has(10)
                        .of(
                            ShortUrlRequestResponseJson.class,
                            Templates.SHORT_URL_REQUEST_RESPONSE_JSON));
              }
            });
  }
}
