package br.com.shortener.converters;

import br.com.shortener.domains.ShortUrlStatistics;
import br.com.shortener.gateways.http.json.response.ShortUrlJson;
import br.com.shortener.gateways.http.json.response.ShortUrlRequestResponseJson;
import br.com.shortener.gateways.http.json.response.ShortUrlStatisticsResponseJson;
import br.com.shortener.gateways.http.json.response.builders.ShortUrlStatisticsResponseJsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShortUrlStatisticsConverter {

  private final ShortUrlRequestConverter shortUrlRequestConverter;

  private final ShortUrlConverter shortUrlConverter;

  @Autowired
  public ShortUrlStatisticsConverter(
      final ShortUrlRequestConverter shortUrlRequestConverter,
      final ShortUrlConverter shortUrlConverter) {
    this.shortUrlRequestConverter = shortUrlRequestConverter;
    this.shortUrlConverter = shortUrlConverter;
  }

  public ShortUrlStatisticsResponseJson convertToShortUrlResponseJson(
      final ShortUrlStatistics shortUrlStatistics) {

    ShortUrlRequestResponseJson lastRequest = null;
    List<ShortUrlRequestResponseJson> shortUrlRequestResponseJsonList = new ArrayList<>();

    if (!CollectionUtils.isEmpty(shortUrlStatistics.getLastTenRequests())) {
      shortUrlStatistics.getLastTenRequests().stream()
          .forEach(
              shortUrlRequest ->
                  shortUrlRequestResponseJsonList.add(
                      shortUrlRequestConverter.convertToShortUrlResponseJson(shortUrlRequest)));

      lastRequest =
          shortUrlRequestResponseJsonList.stream()
              .sorted((o1, o2) -> o2.getRequestDateTime().compareTo(o1.getRequestDateTime()))
              .findFirst()
              .get();
    }

    final ShortUrlJson shortUrlJson =
        shortUrlConverter.convertToShortUrlJson(shortUrlStatistics.getShortUrl());

    return new ShortUrlStatisticsResponseJsonBuilder()
        .setShortUrl(shortUrlJson)
        .setNumberOfRequests(shortUrlStatistics.getNumberOfRequests())
        .setLastRequestDateTime(lastRequest)
        .setLastTenRequests(shortUrlRequestResponseJsonList)
        .createShortUrlStatisticsResponseJson();
  }
}
