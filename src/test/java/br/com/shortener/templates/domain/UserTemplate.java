package br.com.shortener.templates.domain;

import br.com.shortener.domains.collections.User;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

public class UserTemplate implements TemplateLoader {

  @Override
  public void load() {
    Fixture.of(User.class)
        .addTemplate(
            Templates.USER,
            new Rule() {
              {
                add("username", "user-tests");
                add("password", "123456");
                add("authorities", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
              }
            });
  }
}
