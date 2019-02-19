package br.com.shortener.config.mongobee;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongobeeConfig {

  @Autowired private Environment environment;

  @Autowired private MongoTemplate mongoTemplate;

  @Bean
  public Mongobee mongobee() {
    String uri = environment.getProperty("spring.data.mongodb.uri");

    Mongobee mongobee = new Mongobee(uri);
    mongobee.setSpringEnvironment(environment);
    mongobee.setChangeLogsScanPackage("br.com.shortener.config.mongobee.changelog");
    mongobee.setMongoTemplate(mongoTemplate);
    mongobee.setEnabled(true);

    return mongobee;
  }
}
