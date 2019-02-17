package br.com.shortener.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

@Service
public class RetrieveServerUrlContext {

  private Environment environment;

  @Autowired
  public RetrieveServerUrlContext(Environment environment) {
    this.environment = environment;
  }

  public String execute(String protocol) {
    URL shortUrl = null;

    final int port = Integer.valueOf(environment.getProperty("server.port"));

    try {
      final String hostAddress = InetAddress.getLocalHost().getHostAddress();

      shortUrl = new URL(protocol, hostAddress, port, "");
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return String.valueOf(shortUrl);
  }
}
