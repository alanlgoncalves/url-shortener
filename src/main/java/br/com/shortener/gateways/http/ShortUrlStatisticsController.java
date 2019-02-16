package br.com.shortener.gateways.http;

import br.com.shortener.converters.ShortUrlStatisticsConverter;
import br.com.shortener.domains.ShortUrlStatistics;
import br.com.shortener.exceptions.InvalidUrlException;
import br.com.shortener.gateways.http.json.response.ShortUrlStatisticsResponseJson;
import br.com.shortener.usecases.RetrieveShortUrlStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ShortUrlStatisticsController {

  private final RetrieveShortUrlStatistics retrieveShortUrlStatistics;

  private final ShortUrlStatisticsConverter shortUrlStatisticsConverter;

  @Autowired
  public ShortUrlStatisticsController(
      final RetrieveShortUrlStatistics retrieveShortUrlStatistics,
      final ShortUrlStatisticsConverter shortUrlStatisticsConverter) {
    this.retrieveShortUrlStatistics = retrieveShortUrlStatistics;
    this.shortUrlStatisticsConverter = shortUrlStatisticsConverter;
  }

  @GetMapping(value = "short/statistics")
  public ShortUrlStatisticsResponseJson getStatistics(
      @RequestParam("shortUrl") final String shortUrl) {
    final String shortUrlId = getShortUrlId(shortUrl);

    final ShortUrlStatistics shortUrlStatistics = retrieveShortUrlStatistics.execute(shortUrlId);

    return shortUrlStatisticsConverter.convertToShortUrlResponseJson(shortUrlStatistics);
  }

  private String getShortUrlId(final String shortUrl) {
    try {
      URI uri = new URI(shortUrl);
      String path = uri.getPath();
      return path.substring(path.lastIndexOf('/') + 1);
    } catch (URISyntaxException e) {
      throw new InvalidUrlException(String.format("The URI %s is not valid", shortUrl));
    }
  }
}
