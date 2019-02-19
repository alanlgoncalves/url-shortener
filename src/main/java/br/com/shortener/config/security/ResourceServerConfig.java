package br.com.shortener.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.anonymous()
        .disable()
        .authorizeRequests()
        .antMatchers("/oauth/token", "/swagger-ui.html")
        .permitAll()
        .antMatchers("/short-url/create")
        .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        .antMatchers("/short-url/admin/**")
        .access("hasAnyRole('ROLE_ADMIN')")
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new OAuth2AccessDeniedHandler());
  }
}
