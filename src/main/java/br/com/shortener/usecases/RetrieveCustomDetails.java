package br.com.shortener.usecases;

import br.com.shortener.domains.CustomUser;
import br.com.shortener.domains.collections.User;
import br.com.shortener.gateways.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RetrieveCustomDetails implements UserDetailsService {

  private UserGateway userGateway;

  @Autowired
  public RetrieveCustomDetails(UserGateway userGateway) {
    this.userGateway = userGateway;
  }

  @Override
  public CustomUser loadUserByUsername(final String username) {
    User user;

    user = userGateway.getByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User " + username + " was not found in the database");
    }

    return new CustomUser(user);
  }
}
