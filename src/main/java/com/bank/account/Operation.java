package com.bank.account;

import java.math.BigDecimal;
import java.util.Date;

public class Operation {

    private OperationType operationType;
    private Date date;
    private BigDecimal amount;
    // account balance before the Operation
    private BigDecimal balance;

    public Operation(OperationType type, Date date, BigDecimal amount) {
        this.operationType = type;
        this.date = date;
        this.amount = amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return operationType.toString() + " operation at " + date + " of amount : " + amount;
    }
}
