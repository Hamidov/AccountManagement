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

    public synchronized void makeOperation(final Operation operation) {
        if(null == operation) {
            throw new NullPointerException("the operation cannot be NULL");
        }
        switch (operation.getOperationType()) {
            case DEPOSIT:
                deposit(operation);
                break;
            case WITHDRAWAL:
                withdrawal(operation);
                break;
        }
    }

    private void deposit(final Operation operation) {
        if(null == operation.getAmount()) {
            throw new NullPointerException("the amount cannot be NULL");
        }
        if(operation.getAmount().signum() == -1 || operation.getAmount().signum() == 0 ) {
            throw new IllegalArgumentException("we cannot deposit negative or zero amount");
        }
        operations.add(operation);
        operation.setBalance(balance);
        balance  = balance.add(operation.getAmount());
    }

    private void withdrawal(final Operation operation) {
        if(null == operation.getAmount()) {
            throw new NullPointerException("the amount cannot be NULL");
        }
        if(operation.getAmount().signum() == -1 || operation.getAmount().signum() == 0 ) {
            throw new IllegalArgumentException("we cannot withdrawal negative or zero amount");
        }
        if(balance.compareTo(operation.getAmount()) == -1) {
            LOGGER.warn("you don't have enough to make this withdrawal");
            return;
        }
        operations.add(operation);
        operation.setBalance(balance);
        balance  = balance.subtract(operation.getAmount());
    }

    public void showHistory() {
        LOGGER.info("the List of operations : ");
        operations.forEach(operation -> LOGGER.info(operation.toString()));
    }
}
