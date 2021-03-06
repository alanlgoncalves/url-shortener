package br.com.shortener.gateways.http;

import br.com.shortener.converters.ShortUrlConverter;
import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.http.json.response.ShortUrlResponseJson;
import br.com.shortener.templates.Templates;
import br.com.shortener.usecases.RetrieveShortUrl;
import br.com.shortener.usecases.SaveShortUrl;
import br.com.shortener.usecases.SaveShortUrlRequest;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ContextConfiguration
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ShortUrlControllerTest {

  private ShortUrlController shortUrlController;

  @Mock private SaveShortUrl saveShortUrl;

  @Mock private ShortUrlConverter shortUrlConverter;

  @Mock private SaveShortUrlRequest saveShortUrlRequest;

  @Mock private RetrieveShortUrl retrieveShortUrl;
  private MockMvc mockMvc;

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    shortUrlController =
        new ShortUrlController(
            saveShortUrl, shortUrlConverter, saveShortUrlRequest, retrieveShortUrl);

    mockMvc =
        MockMvcBuilders.standaloneSetup(shortUrlController)
            .setControllerAdvice(new CustomExceptionHandler())
            .build();
  }

  @Test
  public void redirectWithUsingRedirectPrefix() throws Exception {
    final String shortUrlId;
    final ShortUrl shortUrl;

    Given:
    {
      shortUrlId = "123456";
      shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);
    }

    final MvcResult mvcResult;

    When:
    {
      when(retrieveShortUrl.execute(anyString())).thenReturn(shortUrl);

      mvcResult = mockMvc.perform(get("/short-url/{shortUrlId}", shortUrlId)).andReturn();
    }

    Then:
    {
      verify(saveShortUrlRequest, times(1)).execute(any(ShortUrl.class), anyString());
      assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.FOUND.value()));
    }
  }

  @Test
  public void shortenURLWithValidUrl() throws Exception {
    final ShortUrl shortUrl;
    final ShortUrlResponseJson shortUrlResponseJson;
    final String bodyJson;

    Given:
    {
      shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);

      shortUrlResponseJson =
          Fixture.from(ShortUrlResponseJson.class).gimme(Templates.SHORT_URL_RESPONSE_JSON);

      bodyJson = "{\n" + "  \"url\": \"https://www.google.com.br\"\n" + "}";
    }

    final MvcResult mvcResult;

    When:
    {
      when(saveShortUrl.execute(anyString())).thenReturn(shortUrl);
      when(shortUrlConverter.convertToShortUrlResponseJson(anyString(), any(ShortUrl.class)))
          .thenReturn(shortUrlResponseJson);

      mvcResult =
          mockMvc
              .perform(
                  post("/short-url/create")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(bodyJson))
              .andReturn();
    }

    Then:
    {
      assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.CREATED.value()));
      assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.shortUrl"));
      assertThat(
          mvcResult.getResponse().getContentAsString(),
          hasJsonPath("$.shortUrl", equalToIgnoringCase(shortUrlResponseJson.getShortUrl())));
    }
  }

  @Test
  public void shortenURLWithoutUrl() throws Exception {
    final String bodyJson;

    Given:
    {
      bodyJson = "{}";
    }

    final MvcResult mvcResult;

    When:
    {
      mvcResult =
          mockMvc
              .perform(
                  post("/short-url/create")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(bodyJson))
              .andReturn();
    }

    Then:
    {
      assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.BAD_REQUEST.value()));
    }
  }

  @Test
  public void shortenURLWithInvalidUrl() throws Exception {
    final String bodyJson;

    Given:
    {
      bodyJson = "{\n" + "  \"url\": \"test\"\n" + "}";
    }

    final MvcResult mvcResult;

    When:
    {
      mvcResult =
          mockMvc
              .perform(
                  post("/short-url/create")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(bodyJson))
              .andReturn();
    }

    Then:
    {
      assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.BAD_REQUEST.value()));
    }
  }
}
