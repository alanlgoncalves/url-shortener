package br.com.shortener.usecase;

import br.com.shortener.domain.ShortUrl;
import br.com.shortener.domain.builder.ShortUrlBuilder;
import br.com.shortener.gateway.ShortUrlGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveShortUrl {

  private final ShortUrlGateway shortUrlGateway;

  @Autowired
  public SaveShortUrl(final ShortUrlGateway shortUrlGateway) {
    this.shortUrlGateway = shortUrlGateway;
  }

  public ShortUrl execute(final String url) {

    final ShortUrl shortUrl = new ShortUrlBuilder().setUrl(url).createShortUrl();

    return shortUrlGateway.save(shortUrl);
  }
}
