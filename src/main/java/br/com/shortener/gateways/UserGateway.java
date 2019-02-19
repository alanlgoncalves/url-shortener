package br.com.shortener.gateways;

import br.com.shortener.domains.collections.User;
import org.springframework.stereotype.Component;

@Component
public interface UserGateway {

  User getByUsername(final String userName);
}
