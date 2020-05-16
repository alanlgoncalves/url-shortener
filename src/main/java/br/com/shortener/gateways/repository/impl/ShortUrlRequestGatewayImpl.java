package br.com.shortener.gateways.repository.impl;

import br.com.shortener.domains.collections.ShortUrlRequest;
import br.com.shortener.gateways.ShortUrlRequestGateway;
import br.com.shortener.gateways.repository.ShortUrlRequestRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShortUrlRequestGatewayImpl implements ShortUrlRequestGateway {

  private final ShortUrlRequestRepository shortUrlRequestRepository;

  public ShortUrlRequestGatewayImpl(final ShortUrlRequestRepository shortUrlRequestRepository) {
    this.shortUrlRequestRepository = shortUrlRequestRepository;
  }

  @Override
  public Long countRequests(final String shortUrlId) {
    return shortUrlRequestRepository.countShortUrlRequestByShortUrlId(shortUrlId);
  }

  @Override
  public List<ShortUrlRequest> getLastTenRequests(final String shortUrlId) {
    return shortUrlRequestRepository.countShortUrlRequestByShortUrlIdOrderByRequestDateTimeDesc(
        shortUrlId, PageRequest.of(0, 10));
  }

  @Override
  public ShortUrlRequest save(final ShortUrlRequest shortUrlRequest) {
    return shortUrlRequestRepository.save(shortUrlRequest);
  }
}
