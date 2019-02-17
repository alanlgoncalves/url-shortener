package br.com.shortener.usecases;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.domains.collections.ShortUrlRequest;
import br.com.shortener.gateways.ShortUrlRequestGateway;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SaveShortUrlRequestTest {

  @InjectMocks private SaveShortUrlRequest saveShortUrlRequest;

  @Mock private ShortUrlRequestGateway shortUrlRequestGateway;

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  public void saveShortUrlRequestWithSuccess() {
    // GIVEN
    final ShortUrl shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);
    final String requestIp = "127.0.0.1";

    // WHEN
    saveShortUrlRequest.execute(shortUrl, requestIp);

    // THEN
    verify(shortUrlRequestGateway, times(1)).save(any(ShortUrlRequest.class));
  }
}
