package br.com.shortener.domains.builders;

import br.com.shortener.domains.collections.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserBuilder {
  private String username;
  private String password;
  private Collection<GrantedAuthority> authorities;

  public UserBuilder setUsername(String username) {
    this.username = username;
    return this;
  }

  public UserBuilder setPassword(String password) {
    this.password = password;
    return this;
  }

  public UserBuilder setAuthorities(Collection<GrantedAuthority> authorities) {
    this.authorities = authorities;
    return this;
  }

  public User createUser() {
    return new User(username, password, authorities);
  }
}
