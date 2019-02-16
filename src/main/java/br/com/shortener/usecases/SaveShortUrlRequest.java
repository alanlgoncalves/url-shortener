package br.com.shortener.usecases;

import br.com.shortener.domains.builders.ShortUrlRequestBuilder;
import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.domains.collections.ShortUrlRequest;
import br.com.shortener.gateways.ShortUrlRequestGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SaveShortUrlRequest {

  private final ShortUrlRequestGateway shortUrlRequestGateway;

  @Autowired
  public SaveShortUrlRequest(final ShortUrlRequestGateway shortUrlRequestGateway) {
    this.shortUrlRequestGateway = shortUrlRequestGateway;
  }

  @Async
  public void execute(final ShortUrl shortUrl, final String requestIp) {
    final ShortUrlRequest shortUrlRequest =
        new ShortUrlRequestBuilder()
            .setShortUrl(shortUrl)
            .setRequestIp(requestIp)
            .setRequestDateTime(LocalDateTime.now())
            .createShortUrlRequest();

    shortUrlRequestGateway.save(shortUrlRequest);
  }
}
