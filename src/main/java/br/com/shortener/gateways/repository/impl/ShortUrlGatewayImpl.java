package br.com.shortener.gateways.repository.impl;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.ShortUrlGateway;
import br.com.shortener.gateways.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShortUrlGatewayImpl implements ShortUrlGateway {

  private final ShortUrlRepository shortUrlRepository;

  @Autowired
  public ShortUrlGatewayImpl(final ShortUrlRepository shortUrlRepository) {
    this.shortUrlRepository = shortUrlRepository;
  }

  @Override
  public Optional<ShortUrl> get(final String id) {
    return shortUrlRepository.findById(id);
  }

  @Override
  public ShortUrl save(final ShortUrl shortUrl) {
    return shortUrlRepository.save(shortUrl);
  }
}
