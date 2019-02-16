package br.com.shortener.usecases;

import br.com.shortener.domains.ShortUrl;
import br.com.shortener.domains.builder.ShortUrlBuilder;
import br.com.shortener.gateways.ShortUrlGateway;
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
