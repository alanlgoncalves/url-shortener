package br.com.shortener.usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveServerUrlContextTest {

  public static final String HTTP_PROTOCOL = "http";
  @InjectMocks private RetrieveServerUrlContext retrieveServerUrlContext;

  @Mock private Environment environment;

  @Test
  public void retrieveServerContextWithSuccess()
      throws UnknownHostException, MalformedURLException {
    // GIVEN
    final String hostAddress = InetAddress.getLocalHost().getHostAddress();
    final int port = 8080;

    // WHEN
    when(environment.getProperty(anyString())).thenReturn(String.valueOf(port));

    String serverUrl = retrieveServerUrlContext.execute(HTTP_PROTOCOL);

    // THEN
    verify(environment, times(1)).getProperty(anyString());
    assertThat(serverUrl).isEqualTo(new URL(HTTP_PROTOCOL, hostAddress, port, "").toString());
  }
}
