package br.com.shortener.converters;

import br.com.shortener.domains.ShortUrlStatistics;
import br.com.shortener.gateways.http.json.response.ShortUrlStatisticsResponseJson;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Comparator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ShortUrlStatisticsConverterTest {

  @Autowired private ShortUrlStatisticsConverter shortUrlStatisticsConverter;

  @BeforeEach
  public void setUp() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  void convertToShortUrlResponseJsonWithStatistics() {
    final ShortUrlStatistics shortUrlStatistics;
    final LocalDateTime lastRequest;

    Given:
    {
      shortUrlStatistics =
          Fixture.from(ShortUrlStatistics.class).gimme(Templates.SHORT_URL_STATISTICS);

      lastRequest =
          shortUrlStatistics.getLastTenRequests().stream()
              .map(shortUrlRequest -> shortUrlRequest.getRequestDateTime())
              .sorted(Comparator.reverseOrder())
              .findFirst()
              .get();
    }

    final ShortUrlStatisticsResponseJson shortUrlStatisticsResponseJson;

    When:
    {
      shortUrlStatisticsResponseJson =
          shortUrlStatisticsConverter.convertToShortUrlResponseJson(shortUrlStatistics);
    }

    Then:
    {
      assertThat(shortUrlStatisticsResponseJson.getLastRequest().getRequestDateTime())
          .isEqualTo(lastRequest);
      assertThat(shortUrlStatisticsResponseJson.getNumberOfRequests())
          .isEqualTo(shortUrlStatistics.getNumberOfRequests());
      assertThat(shortUrlStatisticsResponseJson.getLastRequests().size())
          .isEqualTo(shortUrlStatistics.getLastTenRequests().size());
    }
  }

  @Test
  void convertToShortUrlResponseJsonWithoutStatistics() {
    final ShortUrlStatistics shortUrlStatistics;

    Given:
    {
      shortUrlStatistics =
          Fixture.from(ShortUrlStatistics.class)
              .gimme(Templates.SHORT_URL_STATISTICS_WITHOUT_HISTORY);
    }

    final ShortUrlStatisticsResponseJson shortUrlStatisticsResponseJson;

    When:
    {
      shortUrlStatisticsResponseJson =
          shortUrlStatisticsConverter.convertToShortUrlResponseJson(shortUrlStatistics);
    }

    Then:
    {
      assertThat(shortUrlStatisticsResponseJson.getNumberOfRequests())
          .isEqualTo(shortUrlStatistics.getNumberOfRequests());
      assertThat(shortUrlStatisticsResponseJson.getLastRequests().size())
          .isEqualTo(shortUrlStatistics.getLastTenRequests().size());
    }
  }
}
