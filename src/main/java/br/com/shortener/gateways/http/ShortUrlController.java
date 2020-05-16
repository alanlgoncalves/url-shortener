package br.com.shortener.gateways.http;

import br.com.shortener.converters.ShortUrlConverter;
import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.http.json.request.ShortUrlRequestJson;
import br.com.shortener.gateways.http.json.response.ShortUrlResponseJson;
import br.com.shortener.usecases.RetrieveShortUrl;
import br.com.shortener.usecases.SaveShortUrl;
import br.com.shortener.usecases.SaveShortUrlRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Api(
    value = "Short URL Controller",
    tags = {"Rest API to create and use Short URL"}
    )
public class ShortUrlController {

  Logger log = LoggerFactory.getLogger(ShortUrlController.class);

  private final SaveShortUrl saveShortUrl;

  private final ShortUrlConverter shortUrlConverter;

  private final SaveShortUrlRequest saveShortUrlRequest;

  private final RetrieveShortUrl retrieveShortUrl;

  @Autowired
  public ShortUrlController(
      final SaveShortUrl saveShortUrl,
      final ShortUrlConverter shortUrlConverter,
      final SaveShortUrlRequest saveShortUrlRequest,
      final RetrieveShortUrl retrieveShortUrl) {
    this.saveShortUrl = saveShortUrl;
    this.shortUrlConverter = shortUrlConverter;
    this.saveShortUrlRequest = saveShortUrlRequest;
    this.retrieveShortUrl = retrieveShortUrl;
  }

  @ApiOperation(value = "Get a Short URL from an ID")
  @GetMapping(value = "short-url/{shortUrlId}")
  public ModelAndView redirectWithUsingRedirectPrefix(
      @PathVariable("shortUrlId") final String shortUrlId, HttpServletRequest request) {

    final ShortUrl shortUrl = retrieveShortUrl.execute(shortUrlId);

    saveShortUrlRequest.execute(shortUrl, request.getRemoteAddr());

    return new ModelAndView(String.format("redirect:%s", shortUrl.getUrl()));
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Authorization",
        value = "An ADMIN OR USER token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @ApiOperation(value = "Create a Short URL")
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(
      value = "short-url/create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ShortUrlResponseJson shortenURL(
      @Valid @RequestBody final ShortUrlRequestJson shortUrlRequestJson,
      final HttpServletRequest request) {

    log.info("Request for create Short URL: {}", shortUrlRequestJson);

    final ShortUrl shortUrl = saveShortUrl.execute(shortUrlRequestJson.getUrl());

    return shortUrlConverter.convertToShortUrlResponseJson(request.getScheme(), shortUrl);
  }
}
