package br.com.shortener.converters;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.http.json.response.ShortUrlJson;
import br.com.shortener.gateways.http.json.response.ShortUrlResponseJson;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ShortUrlConverterTest {

  @Autowired private ShortUrlConverter shortUrlConverter;

  @BeforeEach
  public void setUp() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  void convertToShortUrlResponseJson() {
    final ShortUrl shortUrl;

    Given:
    {
      shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);
    }

    final ShortUrlResponseJson shortUrlResponseJson;

    When:
    {
      shortUrlResponseJson = shortUrlConverter.convertToShortUrlResponseJson("http", shortUrl);
    }

    Then:
    {
      assertThat(shortUrlResponseJson.getShortUrl()).isEqualTo(shortUrlResponseJson.getShortUrl());
    }
  }

  @Test
  void convertToShortUrlJson() {

    final ShortUrl shortUrl;

    Given:
    {
      shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);
    }

    final ShortUrlJson shortUrlJson;

    When:
    {
      shortUrlJson = shortUrlConverter.convertToShortUrlJson(shortUrl);
    }

    Then:
    {
      assertThat(shortUrlJson.getUrl()).isEqualTo(shortUrl.getUrl());
    }
  }
}
