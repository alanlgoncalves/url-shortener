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
  void convertToShortUrlResponseJson() {
    // GIVEN
    final ShortUrlStatistics shortUrlStatistics =
        Fixture.from(ShortUrlStatistics.class).gimme(Templates.SHORT_URL_STATISTICS);

    final LocalDateTime lastRequest =
        shortUrlStatistics.getLastTenRequests().stream()
            .map(shortUrlRequest -> shortUrlRequest.getRequestDateTime())
            .sorted(Comparator.reverseOrder())
            .findFirst()
            .get();

    // WHEN
    ShortUrlStatisticsResponseJson shortUrlStatisticsResponseJson =
        shortUrlStatisticsConverter.convertToShortUrlResponseJson(shortUrlStatistics);

    // THEN
    assertThat(shortUrlStatisticsResponseJson.getLastRequest().getRequestDateTime())
        .isEqualTo(lastRequest);
    assertThat(shortUrlStatisticsResponseJson.getNumberOfRequests())
        .isEqualTo(shortUrlStatistics.getNumberOfRequests());
    assertThat(shortUrlStatisticsResponseJson.getLastTenRequests().size())
        .isEqualTo(shortUrlStatistics.getLastTenRequests().size());
  }
}
