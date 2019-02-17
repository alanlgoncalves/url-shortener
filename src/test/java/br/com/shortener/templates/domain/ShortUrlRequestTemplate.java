package br.com.shortener.templates.domain;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.domains.collections.ShortUrlRequest;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalDateTime;

public class ShortUrlRequestTemplate implements TemplateLoader {

  @Override
  public void load() {
    Fixture.of(ShortUrlRequest.class)
        .addTemplate(
            Templates.SHORT_URL_REQUEST_1,
            new Rule() {
              {
                add("shortUrl", one(ShortUrl.class, Templates.SHORT_URL));
                add("requestIp", "127.0.0.1");
                add("requestDateTime", LocalDateTime.of(2019, 1, 1, 10, 0));
              }
            });

    Fixture.of(ShortUrlRequest.class)
        .addTemplate(
            Templates.SHORT_URL_REQUEST_2,
            new Rule() {
              {
                add("shortUrl", one(ShortUrl.class, Templates.SHORT_URL));
                add("requestIp", "127.0.0.1");
                add("requestDateTime", LocalDateTime.of(2019, 1, 3, 10, 0));
              }
            });
  }
}
