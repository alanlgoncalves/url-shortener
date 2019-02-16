package br.com.shortener.usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveServerUrlContextTest {

  @InjectMocks private RetrieveServerUrlContext retrieveServerUrlContext;

  @Mock private ApplicationContext applicationContext;

  @Mock private Environment environment;

  @Test
  public void retrieveServerContextWithSuccess() throws UnknownHostException {
    // GIVEN
    final String ip = InetAddress.getLocalHost().getHostAddress();
    final int port = 8080;

    // WHEN
    when(applicationContext.getBean(any(Class.class))).thenReturn(environment);
    when(environment.getProperty(anyString(), any(Class.class))).thenReturn(port);

    String serverUrl = retrieveServerUrlContext.execute();

    // THEN
    verify(applicationContext, times(1)).getBean(any(Class.class));
    verify(environment, times(1)).getProperty(anyString(), any(Class.class));
    assertThat(serverUrl).isEqualTo(String.format("%s:%s", ip, port));
  }
}
