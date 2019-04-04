package de.adorsys.workshop.banking.infrastructure;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class WebApplication {
  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);

    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(HelloWorldServlet.class, "/");
    server.setHandler(servletHandler);

    server.start();
    server.dumpStdErr();
    server.join();
  }
}
