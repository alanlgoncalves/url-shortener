package br.com.shortener.usecases;

import br.com.shortener.domains.ShortUrlStatistics;
import br.com.shortener.domains.builders.ShortUrlStatisticsBuilder;
import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.domains.collections.ShortUrlRequest;
import br.com.shortener.exceptions.RecordNotFoundException;
import br.com.shortener.gateways.ShortUrlGateway;
import br.com.shortener.gateways.ShortUrlRequestGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetrieveShortUrlStatistics {

  private final ShortUrlGateway shortUrlGateway;
  private final ShortUrlRequestGateway shortUrlRequestGateway;

  public RetrieveShortUrlStatistics(
      final ShortUrlGateway shortUrlGateway, final ShortUrlRequestGateway shortUrlRequestGateway) {
    this.shortUrlGateway = shortUrlGateway;
    this.shortUrlRequestGateway = shortUrlRequestGateway;
  }

  public ShortUrlStatistics execute(final String shortUrlId) {
    Optional<ShortUrl> shortUrlOptional = shortUrlGateway.get(shortUrlId);

    if (!shortUrlOptional.isPresent()) {
      throw new RecordNotFoundException("Short URL not found");
    }

    final Long numberOfRequests = shortUrlRequestGateway.countRequests(shortUrlId);
    final List<ShortUrlRequest> lastTenRequests =
        shortUrlRequestGateway.getLastTenRequests(shortUrlId);

    return new ShortUrlStatisticsBuilder()
        .setShortUrl(shortUrlOptional.get())
        .setNumberOfRequests(numberOfRequests)
        .setLastTenRequests(lastTenRequests)
        .createShortUrlStatistics();
  }
}
