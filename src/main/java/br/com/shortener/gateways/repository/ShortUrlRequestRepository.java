package br.com.shortener.gateways.repository;

import br.com.shortener.domains.collections.ShortUrlRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortUrlRequestRepository extends MongoRepository<ShortUrlRequest, String> {

  long countShortUrlRequestByShortUrlId(final String shortUrlId);

  List<ShortUrlRequest> countShortUrlRequestByShortUrlIdOrderByRequestDateTimeDesc(
      final String shortUrlId, final Pageable pageable);
}
