package br.com.shortener.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class RetrieveServerContext {

  @Autowired private ApplicationContext applicationContext;

  public String execute() {
    String serverContext = "";

    try {
      final String ip = InetAddress.getLocalHost().getHostAddress();

      final int port =
          applicationContext
              .getBean(Environment.class)
              .getProperty("server.port", Integer.class, 8080);

      serverContext = String.format("%s:%s", ip,  port);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

    return serverContext;
  }
}
