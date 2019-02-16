package br.com.shortener.gateways;

import br.com.shortener.domains.collections.ShortUrl;

import java.util.Optional;

public interface ShortUrlGateway {

  Optional<ShortUrl> get(final String id);

  ShortUrl save(final ShortUrl shortUrl);
}
