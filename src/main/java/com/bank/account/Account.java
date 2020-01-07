package com.bank.account;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private static Logger LOGGER = Logger.getLogger(Account.class);
    private String firstName;
    private String lastName;
    private String iban;
    private BigDecimal balance;
    private List<Operation> operations;

    public Account(String firstName, String lastName, String iban) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.iban = iban;
        this.operations = new ArrayList<>();
        this.balance = new BigDecimal(0);

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
