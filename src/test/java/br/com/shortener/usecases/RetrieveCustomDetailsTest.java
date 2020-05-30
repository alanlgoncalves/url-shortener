package br.com.shortener.usecases;

import br.com.shortener.domains.CustomUser;
import br.com.shortener.domains.collections.User;
import br.com.shortener.gateways.UserGateway;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveCustomDetailsTest {

  @InjectMocks private RetrieveCustomDetails retrieveCustomDetails;

  @Mock private UserGateway userGateway;

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  public void loadUserByUsernameWithSuccess() {
    final String username;
    final User user;

    Given:
    {
      username = "user";
      user = Fixture.from(User.class).gimme(Templates.USER);
    }

    final CustomUser customUser;
    When:
    {
      when(userGateway.getByUsername(eq(username))).thenReturn(user);
      customUser = retrieveCustomDetails.loadUserByUsername(username);
    }

    // THEN
    Then:
    {
      verify(userGateway, times(1)).getByUsername(anyString());
      assertThat(customUser.getUsername()).isEqualTo(user.getUsername());
      assertThat(customUser.getPassword()).isEqualTo(user.getPassword());
      assertThat(customUser.getAuthorities().size()).isEqualTo(user.getAuthorities().size());
    }
  }

  @Test(expected = UsernameNotFoundException.class)
  public void loadUserByUsernameNotFound() {
    final String username;

    Given:
    {
      username = "user";
    }

    When:
    {
      when(userGateway.getByUsername(eq(username))).thenReturn(null);
      retrieveCustomDetails.loadUserByUsername(username);
    }

    Then:
    {
      verify(userGateway, times(1)).getByUsername(anyString());
    }
  }
}
