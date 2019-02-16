package br.com.shortener.gateway.repository.Impl;

import br.com.shortener.domain.ShortUrl;
import br.com.shortener.gateway.ShortUrlGateway;
import br.com.shortener.gateway.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlGatewayImpl implements ShortUrlGateway {

  private final ShortUrlRepository shortUrlRepository;

  @Autowired
  public ShortUrlGatewayImpl(final ShortUrlRepository shortUrlRepository) {
    this.shortUrlRepository = shortUrlRepository;
  }

  @Override
  public ShortUrl save(final ShortUrl shortUrl) {
    return shortUrlRepository.save(shortUrl);
  }
}
