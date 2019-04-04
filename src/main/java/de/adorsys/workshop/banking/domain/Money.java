package de.adorsys.workshop.banking.domain;

import java.util.Objects;

public class Money {

  private String currency;
  private int amount;

  public Money(String currency, int amount) {
    if (currency == null) {
      throw new IllegalArgumentException("currency must not be null");
    }

    this.currency = currency;
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public int getAmount() {
    return amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Money money = (Money) o;
    return amount == money.amount &&
            currency.equals(money.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currency, amount);
  }
}
