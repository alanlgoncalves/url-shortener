package br.com.shortener.usecases;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.exceptions.RecordNotFoundException;
import br.com.shortener.gateways.ShortUrlGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetrieveShortUrl {

  private final ShortUrlGateway shortUrlGateway;

  @Autowired
  public RetrieveShortUrl(final ShortUrlGateway shortUrlGateway) {
    this.shortUrlGateway = shortUrlGateway;
  }

  public ShortUrl execute(final String shortUrlId) {
    final Optional<ShortUrl> shortUrlOptional = shortUrlGateway.get(shortUrlId);

    if (!shortUrlOptional.isPresent()) {
      throw new RecordNotFoundException("Short URL not found");
    }

    return shortUrlOptional.get();
  }
}
