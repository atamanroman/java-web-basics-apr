package de.adorsys.workshop.banking.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {

    private String iban;
    private String bic;
    private String owner;
    private List<Transaction> transactions;
    private Money saldo;

    public Account(String iban, String bic, Money saldo, String owner) {
        this(iban, bic, saldo, owner, new ArrayList<>());
    }

    public Account(String iban, String bic, Money saldo, String owner,
                   List<Transaction> transactions) {
        if (iban == null) {
            throw new IllegalArgumentException("iban must not be null");
        }
        if (bic == null) {
            throw new IllegalArgumentException("bic must not be null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner must not be null");
        }
        if (transactions == null) {
            throw new IllegalArgumentException("transactions must not be null");
        }
        if (saldo == null) {
            throw new IllegalArgumentException("saldo must not be null");
        }

        this.iban = iban;
        this.bic = bic;
        this.saldo = saldo;
        this.owner = owner;
        this.transactions = transactions;
    }

    public String getIban() {
        return iban;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public String getOwner() {
        return owner;
    }

    public Money getSaldo() {
        return saldo;
    }

    public String getBic() {
        return bic;
    }

    public Transaction sendMoney(String creditor, Money amount, String text) {
        if (creditor == null) {
            throw new IllegalArgumentException("creditor must not be null");
        }
        if (amount == null) {
            throw new IllegalArgumentException("amount must not be null");
        }
        if (text == null) {
            throw new IllegalArgumentException("text must not be null");
        }
        if (!Objects.equals(amount.getCurrency(), saldo.getCurrency())) {
            throw new ConversionNeededException();
        }
        if (amount.getAmount() > this.saldo.getAmount()) {
            throw new OverdraftNotAllowedException();
        }

        saldo = new Money(saldo.getCurrency(), saldo.getAmount() - amount.getAmount());
        Transaction newTx = new Transaction(getIban(), creditor, amount, LocalDate.now(), text);
        transactions.add(newTx);
        return newTx;
    }
}
