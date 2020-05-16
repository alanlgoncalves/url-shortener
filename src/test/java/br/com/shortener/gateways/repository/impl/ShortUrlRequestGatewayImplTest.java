package br.com.shortener.gateways.repository.impl;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.domains.collections.ShortUrlRequest;
import br.com.shortener.gateways.repository.ShortUrlRequestRepository;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ShortUrlRequestGatewayImplTest {

  @Autowired private ShortUrlRequestGatewayImpl shortUrlRequestGateway;

  @Autowired private ShortUrlRequestRepository shortUrlRequestRepository;

  private ShortUrl shortUrl;

  @BeforeEach
  void setUp() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);

    ShortUrlRequest shortUrlRequest1 =
        Fixture.from(ShortUrlRequest.class).gimme(Templates.SHORT_URL_REQUEST_1);

    ShortUrlRequest shortUrlRequest2 =
        Fixture.from(ShortUrlRequest.class).gimme(Templates.SHORT_URL_REQUEST_2);

    shortUrlRequestRepository.deleteAll();

    shortUrlRequestGateway.save(shortUrlRequest1);
    shortUrlRequestGateway.save(shortUrlRequest2);

    this.shortUrl = shortUrlRequest1.getShortUrl();
  }

  @Test
  void countRequestsWithSuccess() {
    // GIVEN
    String shortUrlId = shortUrl.getId();

    // WHEN
    Long shortUrlNumberOfRequests = shortUrlRequestGateway.countRequests(shortUrlId);

    // THEN
    assertThat(shortUrlNumberOfRequests).isEqualTo(2);
  }

  @Test
  void getLastTenRequestsWithSuccess() {
    // GIVEN
    String shortUrlId = shortUrl.getId();

    // WHEN
    List<ShortUrlRequest> lastTenRequests = shortUrlRequestGateway.getLastTenRequests(shortUrlId);

    // THEN
    assertThat(lastTenRequests.size()).isEqualTo(2);
  }

  @Test
  void saveShortUrlRequestWithSuccess() {
    // GIVEN
    ShortUrlRequest shortUrlRequest1 =
        Fixture.from(ShortUrlRequest.class).gimme(Templates.SHORT_URL_REQUEST_1);

    // WHEN
    ShortUrlRequest saveShortUrlRequest = shortUrlRequestGateway.save(shortUrlRequest1);

    // THEN
    assertThat(saveShortUrlRequest.getRequestIp()).isEqualTo(shortUrlRequest1.getRequestIp());
    assertThat(saveShortUrlRequest.getShortUrl()).isEqualTo(shortUrlRequest1.getShortUrl());
    assertThat(saveShortUrlRequest.getRequestDateTime())
        .isEqualTo(shortUrlRequest1.getRequestDateTime());
  }
}
