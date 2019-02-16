package br.com.shortener.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class RetrieveServerUrlContext {

  public static final String LOCALHOST_IP = "127.0.0.1";

  private final ApplicationContext applicationContext;

  @Autowired
  public RetrieveServerUrlContext(final ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public String execute() {
    String serverContext;

    final int port =
        applicationContext.getBean(Environment.class).getProperty("server.port", Integer.class);

    try {
      final String ip = InetAddress.getLocalHost().getHostAddress();

      serverContext = String.format("%s:%s", ip, port);
    } catch (UnknownHostException e) {
      serverContext = String.format("%s:%s", LOCALHOST_IP, port);
    }

    return serverContext;
  }
}
