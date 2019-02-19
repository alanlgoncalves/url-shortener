package br.com.shortener.gateways.repository.Impl;

import br.com.shortener.domains.collections.User;
import br.com.shortener.gateways.repository.UserRepository;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserGatewayImplTest {

  @Autowired private UserGatewayImpl userGateway;

  @Autowired private UserRepository userRepository;

  private User user;

  @BeforeEach
  void setUp() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);

    final User user = Fixture.from(User.class).gimme(Templates.USER);

    this.user = userRepository.save(user);
  }

  @Test
  public void getByUsername() {
    // GIVEN
    final String username = this.user.getUsername();

    // WHEN
    User user = userGateway.getByUsername(username);

    // THEN
    assertThat(user).isEqualTo(this.user);
  }
}
