package br.com.shortener.gateways.repository;

import br.com.shortener.domains.collections.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  User findByUsername(String userName);
}
