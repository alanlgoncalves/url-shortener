package br.com.shortener.converters;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.http.json.response.ShortUrlResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlConverter {

  public static final String SHORT_PATH_CONTEXT = "short-url";

  private Environment environment;

  @Autowired
  public ShortUrlConverter(Environment environment) {
    this.environment = environment;
  }

  public ShortUrlResponseJson convertToShortUrlResponseJson(
      final String protocol, final ShortUrl shortUrl) {

    String serverAddress = environment.getProperty("server.host");

    final String url =
        String.format(
            "%s://%s/%s/%s", protocol, serverAddress, SHORT_PATH_CONTEXT, shortUrl.getId());

    return new ShortUrlResponseJson(url);
  }
}
