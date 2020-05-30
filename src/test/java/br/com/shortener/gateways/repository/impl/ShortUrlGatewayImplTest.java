package br.com.shortener.gateways.repository.impl;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.ShortUrlGateway;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ShortUrlGatewayImplTest {

  @Autowired private ShortUrlGateway shortUrlGateway;

  @BeforeEach
  public void setUp() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  public void saveAndFindShortUrl() {
    final ShortUrl shortUrl;

    Given:
    {
      shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);
    }

    final ShortUrl saveShortUrl;

    When:
    {
      saveShortUrl = shortUrlGateway.save(shortUrl);
    }

    Then:
    {
      assertThat(shortUrlGateway.get(saveShortUrl.getId()).isPresent()).isEqualTo(true);
      assertThat(shortUrlGateway.get(saveShortUrl.getId()).get().getId())
          .isEqualTo(saveShortUrl.getId());
      assertThat(shortUrlGateway.get(saveShortUrl.getId()).get().getUrl())
          .isEqualTo(saveShortUrl.getUrl());
    }
  }

  @Test
  public void searchInvalidShortUrl() {
    final String shortUrlId;

    Given:
    {
      shortUrlId = "123456";
    }

    final Optional<ShortUrl> shortUrlOptional;

    When:
    {
      shortUrlOptional = shortUrlGateway.get(shortUrlId);
    }

    Then:
    {
      assertThat(shortUrlOptional.isPresent()).isEqualTo(false);
    }
  }
}
