package com.Banking.Retail_Banking.utils;


import java.util.Objects;

import jakarta.validation.constraints.Positive;

public class WithdrawInput extends AccountInput{
    String sortCode;
    String accountNumber;


    @Positive(message = "Transfer amount must be positive")
    private double amount;

    public WithdrawInput() {
        this.sortCode = super.getSortCode();
        this.accountNumber = super.getAccountNumber();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AccountInput{" +
                "sortCode='" + sortCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithdrawInput that = (WithdrawInput) o;
        return Objects.equals(sortCode, that.sortCode) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortCode, accountNumber, amount);
    }
}
