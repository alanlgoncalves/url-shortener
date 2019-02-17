package br.com.shortener.converters;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.http.json.response.ShortUrlResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlConverter {

  public static final String SHORT_PATH_CONTEXT = "short";

  private Environment environment;

  @Autowired
  public ShortUrlConverter(Environment environment) {
    this.environment = environment;
  }

  public ShortUrlResponseJson convertToShortUrlResponseJson(final ShortUrl shortUrl) {

    String serverAddress = environment.getProperty("server.dns");

    final String url =
        String.format("%s/%s/%s", serverAddress, SHORT_PATH_CONTEXT, shortUrl.getId());

    return new ShortUrlResponseJson(url);
  }
}
