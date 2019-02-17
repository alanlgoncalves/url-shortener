package br.com.shortener.usecases;

import br.com.shortener.domains.ShortUrlStatistics;
import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.domains.collections.ShortUrlRequest;
import br.com.shortener.exceptions.RecordNotFoundException;
import br.com.shortener.gateways.ShortUrlGateway;
import br.com.shortener.gateways.ShortUrlRequestGateway;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveShortUrlStatisticsTest {

  @InjectMocks private RetrieveShortUrlStatistics retrieveShortUrlStatistics;

  @Mock private ShortUrlGateway shortUrlGateway;

  @Mock private ShortUrlRequestGateway shortUrlRequestGateway;

  @Before
  public void setUp() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  public void retrieveShortUrlStatisticsWithResults() {
    // GIVEN
    final String shortUrlId = "123456";
    final ShortUrl shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);
    final long numberOfRequests = 10l;
    final List<ShortUrlRequest> shortUrlRequestList =
        Fixture.from(ShortUrlRequest.class).gimme(10, Templates.SHORT_URL_REQUEST_1);

    // WHEN
    when(shortUrlGateway.get(eq(shortUrlId))).thenReturn(Optional.of(shortUrl));
    when(shortUrlRequestGateway.countRequests(eq(shortUrlId))).thenReturn(numberOfRequests);
    when(shortUrlRequestGateway.getLastTenRequests(eq(shortUrlId))).thenReturn(shortUrlRequestList);

    ShortUrlStatistics shortUrlStatistics = retrieveShortUrlStatistics.execute(shortUrlId);

    // THEN
    verify(shortUrlGateway, times(1)).get(eq(shortUrlId));
    verify(shortUrlRequestGateway, times(1)).countRequests(eq(shortUrlId));
    verify(shortUrlRequestGateway, times(1)).getLastTenRequests(eq(shortUrlId));

    assertThat(shortUrlStatistics.getShortUrl()).isEqualTo(shortUrl);
    assertThat(shortUrlStatistics.getLastTenRequests().size()).isEqualTo(numberOfRequests);
    assertThat(shortUrlStatistics.getNumberOfRequests()).isEqualTo(numberOfRequests);
  }

  @Test
  public void retrieveShortUrlStatisticsWithNonResults() {
    // GIVEN
    final String shortUrlId = "123456";
    final ShortUrl shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);
    final long numberOfRequests = 0l;
    final List<ShortUrlRequest> shortUrlRequestList = new ArrayList<>();

    // WHEN
    when(shortUrlGateway.get(eq(shortUrlId))).thenReturn(Optional.of(shortUrl));
    when(shortUrlRequestGateway.countRequests(eq(shortUrlId))).thenReturn(numberOfRequests);
    when(shortUrlRequestGateway.getLastTenRequests(eq(shortUrlId))).thenReturn(shortUrlRequestList);

    ShortUrlStatistics shortUrlStatistics = retrieveShortUrlStatistics.execute(shortUrlId);

    // THEN
    verify(shortUrlGateway, times(1)).get(eq(shortUrlId));
    verify(shortUrlRequestGateway, times(1)).countRequests(eq(shortUrlId));
    verify(shortUrlRequestGateway, times(1)).getLastTenRequests(eq(shortUrlId));

    assertThat(shortUrlStatistics.getShortUrl()).isEqualTo(shortUrl);
    assertThat(shortUrlStatistics.getLastTenRequests().size()).isEqualTo(numberOfRequests);
    assertThat(shortUrlStatistics.getNumberOfRequests()).isEqualTo(numberOfRequests);
  }

  @Test(expected = RecordNotFoundException.class)
  public void retrieveShortUrlStatisticsWithNotFoundShortUrl() {
    // GIVEN
    final String shortUrlId = "123456";

    // WHEN
    when(shortUrlGateway.get(eq(shortUrlId))).thenReturn(Optional.empty());

    ShortUrlStatistics shortUrlStatistics = retrieveShortUrlStatistics.execute(shortUrlId);

    // THEN
    verify(shortUrlGateway, times(1)).get(eq(shortUrlId));
    verify(shortUrlRequestGateway, times(0)).countRequests(eq(shortUrlId));
    verify(shortUrlRequestGateway, times(0)).getLastTenRequests(eq(shortUrlId));
  }
}
