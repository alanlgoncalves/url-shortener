package br.com.shortener.config.mongobee;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongobeeConfig {

  @Autowired private Environment environment;

  @Autowired private MongoTemplate mongoTemplate;

  @Value("${spring.data.mongodb.uri:mongodb://127.0.0.1:27017/dbShortener}")
  private String mongoUri;

  @Bean
  public Mongobee mongobee() {
    Mongobee mongobee = new Mongobee(mongoUri);
    mongobee.setSpringEnvironment(environment);
    mongobee.setChangeLogsScanPackage("br.com.shortener.config.mongobee.changelog");
    mongobee.setMongoTemplate(mongoTemplate);
    mongobee.setEnabled(true);

    return mongobee;
  }
}
