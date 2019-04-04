package de.adorsys.workshop.banking.domain;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountTest {

  private Account cut;

  @Before
  public void setup() {
    cut = new Account("DE12345678", "123456", new Money("EUR", 1000), "Roman");
  }

  @Test
  public void accountIsCreatedProperly() {
    assertEquals("DE12345678", cut.getIban());
    assertEquals("123456", cut.getBic());
    assertEquals("EUR", cut.getSaldo().getCurrency());
    assertEquals(1000, cut.getSaldo().getAmount());
    assertEquals("Roman", cut.getOwner());
    assertTrue(cut.getTransactions().isEmpty());
  }

  @Test
  public void noMoneyConversionAllowed() {
    try {
      cut.sendMoney(
        "DE1234567891",
        new Money("USD", 100),
        "test"
      );
      fail("ConversionNeededException not thrown");
    } catch (ConversionNeededException e) {
      // OK
    }
  }

  @Test
  public void saldoIsReduced() {
    cut.sendMoney(
      "DE1234567891",
      new Money("EUR", 100),
      "test"
    );
    assertEquals(900, cut.getSaldo().getAmount());
  }

  @Test
  public void transactionHasRightAmount() {
    Transaction transaction = cut.sendMoney(
      "DE1234567891",
      new Money("EUR", 100),
      "test"
    );
    assertEquals(100, transaction.getMoney().getAmount());
    assertEquals("test", transaction.getText());
    assertEquals("DE1234567891", transaction.getCreditor());
    assertEquals(cut.getIban(), transaction.getDebtor());
  }

}
