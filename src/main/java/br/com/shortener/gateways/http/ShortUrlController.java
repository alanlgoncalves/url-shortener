package br.com.shortener.gateways.http;

import br.com.shortener.converters.ShortUrlConverter;
import br.com.shortener.domains.ShortUrl;
import br.com.shortener.gateways.http.json.request.ShortUrlRequestJson;
import br.com.shortener.gateways.http.json.response.ShortUrlResponseJson;
import br.com.shortener.usecases.RetrieveServerContext;
import br.com.shortener.usecases.RetrieveShortUrl;
import br.com.shortener.usecases.SaveShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ShortUrlController {

  private final SaveShortUrl saveShortUrl;

  private final ShortUrlConverter shortUrlConverter;

  private final RetrieveShortUrl retrieveShortUrl;

  private final RetrieveServerContext retrieveServerContext;

  @Autowired
  public ShortUrlController(
      final SaveShortUrl saveShortUrl,
      final ShortUrlConverter shortUrlConverter,
      final RetrieveShortUrl retrieveShortUrl,
      final RetrieveServerContext retrieveServerContext) {
    this.saveShortUrl = saveShortUrl;
    this.shortUrlConverter = shortUrlConverter;
    this.retrieveShortUrl = retrieveShortUrl;
    this.retrieveServerContext = retrieveServerContext;
  }

  @GetMapping(value = "short/{shortUrlId}")
  public ModelAndView redirectWithUsingRedirectPrefix(
      @PathVariable("shortUrlId") final String shortUrlId) {

    ShortUrl shortUrl = retrieveShortUrl.execute(shortUrlId);

    return new ModelAndView(String.format("redirect:%s", shortUrl.getUrl()));
  }

  @ResponseBody
  @PostMapping(
      value = "short",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ShortUrlResponseJson shortenURL(
      @Valid @RequestBody final ShortUrlRequestJson shortUrlRequestJson) {

    ShortUrl shortUrl = saveShortUrl.execute(shortUrlRequestJson.getUrl());

    return shortUrlConverter.convertToShortUrlResponseJson(
        retrieveServerContext.execute(), shortUrl);
  }
}
