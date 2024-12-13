package com.group34.Model.Cash;

public class CashVault {

    private int balance;

    public CashVault(int balance) {
        assert balance >= 0;
        this.balance = balance;
    }

    /**
     * Returns the balance of the cash vault.
     * @return the balance of the cash vault
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Deposits the given amount into the balance.
     * @param amount the amount to deposit
     */
    public void deposit(int amount) {
        assert amount >= 0;
        balance += amount;
    }

    /**
     * Reduces the balance by the given amount.
     * @param amount the amount to reduce the balance by
     * @throws OverDraftError if the balance would go below 0
     */
    public void reduce(int amount) throws OverDraftError {
        if (balance < amount) {
            throw new OverDraftError("Not enough Money");
        }
        balance -= amount;
    }
}
