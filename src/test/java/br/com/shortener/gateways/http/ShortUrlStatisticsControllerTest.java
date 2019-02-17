package br.com.shortener.gateways.http;

import br.com.shortener.converters.ShortUrlStatisticsConverter;
import br.com.shortener.domains.ShortUrlStatistics;
import br.com.shortener.exceptions.RecordNotFoundException;
import br.com.shortener.gateways.http.json.response.ShortUrlStatisticsResponseJson;
import br.com.shortener.templates.Templates;
import br.com.shortener.usecases.RetrieveShortUrlStatistics;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ShortUrlStatisticsControllerTest {

  private ShortUrlStatisticsController shortUrlStatisticsController;

  @Mock private RetrieveShortUrlStatistics retrieveShortUrlStatistics;

  @Mock private ShortUrlStatisticsConverter shortUrlStatisticsConverter;

  private MockMvc mockMvc;

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    shortUrlStatisticsController =
        new ShortUrlStatisticsController(retrieveShortUrlStatistics, shortUrlStatisticsConverter);

    mockMvc =
        MockMvcBuilders.standaloneSetup(shortUrlStatisticsController)
            .setControllerAdvice(new CustomExceptionHandler())
            .build();
  }

  @Test
  public void getStatisticsWithSuccess() throws Exception {

    // GIVEN
    final String shortUrl = "http://127.0.0.1/123456";
    final ShortUrlStatistics shortUrlStatistics =
        Fixture.from(ShortUrlStatistics.class).gimme(Templates.SHORT_URL_STATISTICS);
    final ShortUrlStatisticsResponseJson shortUrlStatisticsResponseJson =
        Fixture.from(ShortUrlStatisticsResponseJson.class)
            .gimme(Templates.SHORT_URL_STATISTICS_RESPONSE_JSON);

    // WHEN
    when(retrieveShortUrlStatistics.execute(anyString())).thenReturn(shortUrlStatistics);
    when(shortUrlStatisticsConverter.convertToShortUrlResponseJson(any(ShortUrlStatistics.class)))
        .thenReturn(shortUrlStatisticsResponseJson);

    final MvcResult mvcResult =
        mockMvc.perform(get("/short/statistics").param("shortUrl", shortUrl)).andReturn();

    // THEN
    assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.OK.value()));
    assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.numberOfRequests"));
    assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.shortUrl"));
    assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.lastRequest"));
    assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.lastRequests"));
    assertThat(
        mvcResult.getResponse().getContentAsString(),
        hasJsonPath("$.numberOfRequests", equalTo(10)));
  }

  @Test
  public void getStatisticsNotFound() throws Exception {

    // GIVEN
    final String shortUrl = "http://127.0.0.1/123456";

    // WHEN
    doThrow(RecordNotFoundException.class).when(retrieveShortUrlStatistics).execute(anyString());

    final MvcResult mvcResult =
        mockMvc.perform(get("/short/statistics").param("shortUrl", shortUrl)).andReturn();

    // THEN
    assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.NOT_FOUND.value()));
  }

  @Test
  public void getStatisticsWithInvalidUrl() throws Exception {

    // GIVEN
    final String shortUrl = "test";

    // WHEN
    doThrow(RecordNotFoundException.class).when(retrieveShortUrlStatistics).execute(anyString());

    final MvcResult mvcResult =
        mockMvc.perform(get("/short/statistics").param("shortUrl", shortUrl)).andReturn();

    // THEN
    assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
  }
}
