package br.com.shortener.converters;

import br.com.shortener.domains.collections.ShortUrlRequest;
import br.com.shortener.gateways.http.json.response.ShortUrlRequestResponseJson;
import br.com.shortener.gateways.http.json.response.builders.ShortUrlRequestJsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlRequestConverter {

  public ShortUrlRequestResponseJson convertToShortUrlResponseJson(
      final ShortUrlRequest shortUrlRequest) {
    return new ShortUrlRequestJsonBuilder()
        .setRequestDateTime(shortUrlRequest.getRequestDateTime())
        .createShortUrlRequestJson();
  }
}
