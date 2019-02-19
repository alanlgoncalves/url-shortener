package br.com.shortener.config.mongobee.changelog;

import br.com.shortener.domains.builders.UserBuilder;
import br.com.shortener.domains.collections.User;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ChangeLog
public class DataBaseChangeLog {

  @ChangeSet(order = "001", id = "initialUsersData", author = "alanlgoncalves")
  public void initialUsersData(MongoTemplate mongoTemplate) {

    List<User> users = new ArrayList<>();

    users.add(
        new UserBuilder()
            .setUsername("admin")
            .setPassword(new BCryptPasswordEncoder().encode("!@AdminPassword#$123"))
            .setAuthorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))
            .createUser());

    users.add(
        new UserBuilder()
            .setUsername("user")
            .setPassword(new BCryptPasswordEncoder().encode("!@UserPassword#$123"))
            .setAuthorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")))
            .createUser());

    mongoTemplate.insertAll(users);
  }
}
