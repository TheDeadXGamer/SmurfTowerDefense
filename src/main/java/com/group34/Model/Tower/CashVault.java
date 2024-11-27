package com.group34.Model.Tower;

public class CashVault {

    private int balance;

    public CashVault(int balance) {
        assert balance >= 0;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        assert amount >= 0;
        balance += amount;
    }

    public void reduce(int amount) throws OverDraftError {
        throw new OverDraftError();
    }
}
