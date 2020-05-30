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
import org.junit.BeforeClass;
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

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  public void retrieveShortUrlStatisticsWithResults() {
    final String shortUrlId;
    final ShortUrl shortUrl;
    final long numberOfRequests;
    final List<ShortUrlRequest> shortUrlRequestList;

    Given:
    {
      shortUrlId = "123456";
      shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);
      numberOfRequests = 10l;
      shortUrlRequestList =
          Fixture.from(ShortUrlRequest.class).gimme(10, Templates.SHORT_URL_REQUEST_1);
    }

    final ShortUrlStatistics shortUrlStatistics;

    When:
    {
      when(shortUrlGateway.get(eq(shortUrlId))).thenReturn(Optional.of(shortUrl));
      when(shortUrlRequestGateway.countRequests(eq(shortUrlId))).thenReturn(numberOfRequests);
      when(shortUrlRequestGateway.getLastTenRequests(eq(shortUrlId)))
          .thenReturn(shortUrlRequestList);

      shortUrlStatistics = retrieveShortUrlStatistics.execute(shortUrlId);
    }

    Then:
    {
      verify(shortUrlGateway, times(1)).get(eq(shortUrlId));
      verify(shortUrlRequestGateway, times(1)).countRequests(eq(shortUrlId));
      verify(shortUrlRequestGateway, times(1)).getLastTenRequests(eq(shortUrlId));

      assertThat(shortUrlStatistics.getShortUrl()).isEqualTo(shortUrl);
      assertThat(shortUrlStatistics.getLastTenRequests().size()).isEqualTo(numberOfRequests);
      assertThat(shortUrlStatistics.getNumberOfRequests()).isEqualTo(numberOfRequests);
    }
  }

  @Test
  public void retrieveShortUrlStatisticsWithNonResults() {
    final String shortUrlId;
    final ShortUrl shortUrl;
    final long numberOfRequests;
    final List<ShortUrlRequest> shortUrlRequestList;

    Given:
    {
      shortUrlId = "123456";
      shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);
      numberOfRequests = 0l;
      shortUrlRequestList = new ArrayList<>();
    }

    final ShortUrlStatistics shortUrlStatistics;

    When:
    {
      when(shortUrlGateway.get(eq(shortUrlId))).thenReturn(Optional.of(shortUrl));
      when(shortUrlRequestGateway.countRequests(eq(shortUrlId))).thenReturn(numberOfRequests);
      when(shortUrlRequestGateway.getLastTenRequests(eq(shortUrlId)))
          .thenReturn(shortUrlRequestList);

      shortUrlStatistics = retrieveShortUrlStatistics.execute(shortUrlId);
    }

    Then:
    {
      verify(shortUrlGateway, times(1)).get(eq(shortUrlId));
      verify(shortUrlRequestGateway, times(1)).countRequests(eq(shortUrlId));
      verify(shortUrlRequestGateway, times(1)).getLastTenRequests(eq(shortUrlId));

      assertThat(shortUrlStatistics.getShortUrl()).isEqualTo(shortUrl);
      assertThat(shortUrlStatistics.getLastTenRequests().size()).isEqualTo(numberOfRequests);
      assertThat(shortUrlStatistics.getNumberOfRequests()).isEqualTo(numberOfRequests);
    }
  }

  @Test(expected = RecordNotFoundException.class)
  public void retrieveShortUrlStatisticsWithNotFoundShortUrl() {
    final String shortUrlId;

    Given:
    {
      shortUrlId = "123456";
    }

    When:
    {
      when(shortUrlGateway.get(eq(shortUrlId))).thenReturn(Optional.empty());

      retrieveShortUrlStatistics.execute(shortUrlId);
    }

    Then:
    {
      verify(shortUrlGateway, times(1)).get(eq(shortUrlId));
      verify(shortUrlRequestGateway, times(0)).countRequests(eq(shortUrlId));
      verify(shortUrlRequestGateway, times(0)).getLastTenRequests(eq(shortUrlId));
    }
  }
}
