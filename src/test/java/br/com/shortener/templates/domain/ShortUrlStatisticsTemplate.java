package br.com.shortener.templates.domain;

import br.com.shortener.domains.ShortUrlStatistics;
import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.domains.collections.ShortUrlRequest;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import java.util.ArrayList;

public class ShortUrlStatisticsTemplate implements TemplateLoader {

  @Override
  public void load() {
    Fixture.of(ShortUrlStatistics.class)
        .addTemplate(
            Templates.SHORT_URL_STATISTICS,
            new Rule() {
              {
                add("shortUrl", one(ShortUrl.class, Templates.SHORT_URL));
                add("numberOfRequests", 10l);
                add(
                    "lastTenRequests",
                    has(10).of(ShortUrlRequest.class, Templates.SHORT_URL_REQUEST_1));
              }
            });

    Fixture.of(ShortUrlStatistics.class)
        .addTemplate(
            Templates.SHORT_URL_STATISTICS_WITHOUT_HISTORY,
            new Rule() {
              {
                add("shortUrl", one(ShortUrl.class, Templates.SHORT_URL));
                add("numberOfRequests", 0l);
                add("lastTenRequests", new ArrayList<>());
              }
            });
  }
}
