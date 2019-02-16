package br.com.shortener.gateways;

import br.com.shortener.domains.ShortUrl;

import java.util.Optional;

public interface ShortUrlGateway {

  Optional<ShortUrl> get(String id);

  ShortUrl save(ShortUrl shortUrl);
}
