package de.adorsys.workshop.banking.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.adorsys.workshop.banking.domain.Account;
import de.adorsys.workshop.banking.domain.Money;
import org.graalvm.compiler.lir.CompositeValue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccountServlet extends HttpServlet {

  private Map<String, Account> accounts = new HashMap<>();
  private ObjectMapper om;

  public AccountServlet(ObjectMapper om) {
    this.om = om;
    accounts.put("DE1234567890", new Account("DE1234567890", "1234567", new Money("EUR", 1000), "Roman"));
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    String pathInfo = req.getPathInfo();
    resp.setHeader("content-type", "application/json");
    String body;
    if (pathInfo == null || pathInfo.equals("/")) {
      body = om.writeValueAsString(accounts.values());
    } else {
      String iban = pathInfo.replaceAll("\\/", "");
      body = om.writeValueAsString(accounts.get(iban));
    }
    resp.getWriter().write(body);
  }
}
