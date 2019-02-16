package br.com.shortener.gateways;

import br.com.shortener.domains.collections.ShortUrlRequest;

import java.util.List;

public interface ShortUrlRequestGateway {

  Long countRequests(final String shortUrlId);

  List<ShortUrlRequest> getLastTenRequests(final String shortUrlId);

  ShortUrlRequest save(final ShortUrlRequest shortUrlRequest);
}
