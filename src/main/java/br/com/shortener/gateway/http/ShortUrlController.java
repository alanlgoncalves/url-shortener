package br.com.shortener.gateway.http;

import br.com.shortener.converters.ShortUrlConverter;
import br.com.shortener.domain.ShortUrl;
import br.com.shortener.gateway.http.json.request.ShortUrlRequestJson;
import br.com.shortener.gateway.http.json.response.ShortUrlResponseJson;
import br.com.shortener.usecase.RetrieveServerContext;
import br.com.shortener.usecase.SaveShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class ShortUrlController {

  private final SaveShortUrl saveShortUrl;

  private final ShortUrlConverter shortUrlConverter;

  private final RetrieveServerContext retrieveServerContext;

  @Autowired
  public ShortUrlController(
      final SaveShortUrl saveShortUrl,
      final ShortUrlConverter shortUrlConverter,
      final RetrieveServerContext retrieveServerContext) {
    this.saveShortUrl = saveShortUrl;
    this.shortUrlConverter = shortUrlConverter;
    this.retrieveServerContext = retrieveServerContext;
  }

  @ResponseBody
  @PostMapping(
          value = "short",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ShortUrlResponseJson shortenURL(
          @Valid @RequestBody ShortUrlRequestJson shortUrlRequestJson) {

    ShortUrl shortUrl = saveShortUrl.execute(shortUrlRequestJson.getUrl());

    return shortUrlConverter.convertToShortUrlResponseJson(retrieveServerContext.execute(), shortUrl);
  }
}
