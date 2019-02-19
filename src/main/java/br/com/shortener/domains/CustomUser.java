package br.com.shortener.domains;

import br.com.shortener.domains.collections.User;

public class CustomUser extends org.springframework.security.core.userdetails.User {

  public CustomUser(User user) {
    super(user.getUsername(), user.getPassword(), user.getAuthorities());
  }
}
