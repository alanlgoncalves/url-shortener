package br.com.shortener.gateways.repository.Impl;

import br.com.shortener.domains.collections.User;
import br.com.shortener.gateways.UserGateway;
import br.com.shortener.gateways.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGatewayImpl implements UserGateway {

  private final UserRepository userRepository;

  @Autowired
  public UserGatewayImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
