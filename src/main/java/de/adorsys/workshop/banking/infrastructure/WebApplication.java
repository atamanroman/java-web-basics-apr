package de.adorsys.workshop.banking.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebApplication {
  public static void main(String[] args) throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();

    Server server = new Server(8080);

    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(
      new ServletHolder(new AccountServlet(objectMapper)),
      "/accounts/*"
    );
    server.setHandler(servletHandler);

    server.start();
    server.dumpStdErr();
    server.join();
  }
}
