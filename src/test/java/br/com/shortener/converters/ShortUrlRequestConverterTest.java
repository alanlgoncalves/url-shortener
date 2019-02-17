package br.com.shortener.converters;

import br.com.shortener.domains.collections.ShortUrlRequest;
import br.com.shortener.gateways.http.json.response.ShortUrlRequestResponseJson;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ShortUrlRequestConverterTest {

  @Autowired private ShortUrlRequestConverter shortUrlRequestConverter;

  @BeforeEach
  public void setUp() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  void convertToShortUrlResponseJson() {
    // GIVEN
    ShortUrlRequest shortUrlRequest =
        Fixture.from(ShortUrlRequest.class).gimme(Templates.SHORT_URL_REQUEST_1);

    // WHEN
    ShortUrlRequestResponseJson shortUrlRequestResponseJson =
        shortUrlRequestConverter.convertToShortUrlResponseJson(shortUrlRequest);

    // THEN
    assertThat(shortUrlRequestResponseJson.getRequestIp())
        .isEqualTo(shortUrlRequest.getRequestIp());
    assertThat(shortUrlRequestResponseJson.getRequestDateTime())
        .isEqualTo(shortUrlRequest.getRequestDateTime());
  }
}
