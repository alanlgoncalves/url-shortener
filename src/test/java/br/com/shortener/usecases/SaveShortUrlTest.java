package br.com.shortener.usecases;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.ShortUrlGateway;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaveShortUrlTest {

  @InjectMocks private SaveShortUrl saveShortUrl;

  @Mock private ShortUrlGateway shortUrlGateway;

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  public void saveShortUrlWithSuccess() {
    final String url;
    final ShortUrl shortUrl;

    Given:
    {
      url = "https://www.google.com.br";
      shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);
    }

    final ShortUrl savedShortUrl;

    When:
    {
      when(shortUrlGateway.save(any(ShortUrl.class))).thenReturn(shortUrl);
      savedShortUrl = saveShortUrl.execute(url);
    }

    Then:
    {
      verify(shortUrlGateway, times(1)).save(any(ShortUrl.class));
      assertThat(savedShortUrl.getUrl()).isEqualTo(url);
    }
  }
}
