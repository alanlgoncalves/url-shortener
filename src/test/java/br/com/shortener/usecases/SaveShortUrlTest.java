package br.com.shortener.usecases;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.ShortUrlGateway;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaveShortUrlTest {

  @InjectMocks private SaveShortUrl saveShortUrl;

  @Mock private ShortUrlGateway shortUrlGateway;

  @Before
  public void setUp() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  public void saveShortUrlWithSuccess() {
    // GIVEN
    final String url = "https://www.google.com.br";
    final ShortUrl shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);

    // WHEN
    when(shortUrlGateway.save(any(ShortUrl.class))).thenReturn(shortUrl);
    ShortUrl savedShortUrl = saveShortUrl.execute(url);

    // THEN
    verify(shortUrlGateway, times(1)).save(any(ShortUrl.class));
    assertThat(savedShortUrl.getUrl()).isEqualTo(url);
  }
}
