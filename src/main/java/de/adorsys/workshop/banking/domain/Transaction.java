package de.adorsys.workshop.banking.domain;

import java.time.LocalDate;

public class Transaction {

  private String debtor;
  private String creditor;
  private Money money;
  private String text;
  private LocalDate bookingDate;

  public Transaction(String debtor, String creditor, Money money,
      LocalDate bookingDate, String text) {
    if (debtor == null) {
      throw new IllegalArgumentException("debtor must not be null");
    }
    if (creditor == null) {
      throw new IllegalArgumentException("creditor must not be null");
    }
    if (money == null) {
      throw new IllegalArgumentException("money must not be null");
    }
    if (bookingDate == null) {
      throw new IllegalArgumentException("bookingDate must not be null");
    }
    if (text == null) {
      throw new IllegalArgumentException("text must not be null");
    }

    this.debtor = debtor;
    this.creditor = creditor;
    this.money = money;
    this.bookingDate = bookingDate;
    this.text = text;
  }

  public String getDebtor() {
    return debtor;
  }

  public String getCreditor() {
    return creditor;
  }

  public Money getMoney() {
    return money;
  }

  public LocalDate getBookingDate() {
    return bookingDate;
  }

  public String getText() {
    return text;
  }
}
