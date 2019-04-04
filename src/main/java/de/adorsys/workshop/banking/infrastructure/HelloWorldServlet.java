package de.adorsys.workshop.banking.infrastructure;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorldServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setHeader("content-type", "text/html");
    resp.getWriter().write("<html><body><h1>Hello World</h1></body></html>");
  }
}
