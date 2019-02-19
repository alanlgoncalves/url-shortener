package br.com.shortener.gateways.http;

import br.com.shortener.converters.ShortUrlStatisticsConverter;
import br.com.shortener.domains.ShortUrlStatistics;
import br.com.shortener.exceptions.InvalidUrlException;
import br.com.shortener.gateways.http.json.response.ShortUrlStatisticsResponseJson;
import br.com.shortener.usecases.RetrieveShortUrlStatistics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

@Api(
    value = "Short URL Admin Controller",
    tags = {"Rest API to get statistics from a Short URL"},
    description = "Rest API to get statistics from a Short URL",
    basePath = "/short-url/admin/")
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

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Authorization",
        value = "An ADMIN token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @ApiOperation(value = "Return Short URL statistics")
  @GetMapping(value = "short-url/admin/statistics")
  public ShortUrlStatisticsResponseJson getStatistics(
      @RequestParam("shortUrl") final String shortUrl) {
    final String shortUrlId = getShortUrlId(shortUrl);

    final ShortUrlStatistics shortUrlStatistics = retrieveShortUrlStatistics.execute(shortUrlId);

    return shortUrlStatisticsConverter.convertToShortUrlResponseJson(shortUrlStatistics);
  }

  private String getShortUrlId(final String shortUrl) {
    try {
      URL url = new URL(shortUrl);
      String path = url.getPath();
      return path.substring(path.lastIndexOf('/') + 1);
    } catch (MalformedURLException e) {
      throw new InvalidUrlException(String.format("The URI %s is not valid", shortUrl));
    }
  }
}
