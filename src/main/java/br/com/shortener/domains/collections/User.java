package br.com.shortener.domains.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Document(collection = "users")
public class User {

  @Id private String id;

  private String username;

  private String password;

  private Collection<GrantedAuthority> authorities = new ArrayList<>();

  public User(String username, String password, Collection<GrantedAuthority> authorities) {
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Collection<GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Collection<GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id)
        && Objects.equals(username, user.username)
        && Objects.equals(password, user.password)
        && Objects.equals(authorities, user.authorities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, authorities);
  }
}
