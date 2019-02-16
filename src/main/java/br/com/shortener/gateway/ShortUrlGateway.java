package br.com.shortener.gateway;

import br.com.shortener.domain.ShortUrl;

public interface ShortUrlGateway {

  ShortUrl save(ShortUrl shortUrl);
}
