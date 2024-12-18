package com.group34.Model.Shop;

import java.util.ArrayList;

public class CashVault{
    private ArrayList<CashVaultObserver> observers;
    private int balance;

    public CashVault(int balance) {
        assert balance >= 0;
        this.balance = balance;
        observers = new ArrayList<>();
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
        notifyObservers();
    }

    /**
     * Reduces the balance by the given amount.
     * @param amount the amount to reduce the balance by
     * @throws OverDraftError if the balance would go below 0
     */
    public void withdraw(int amount) throws OverDraftError {
        ableToBuy(amount);
        balance -= amount;
        notifyObservers();
    }

    public boolean ableToBuy(int amount) throws OverDraftError {
        if (balance < amount) {
            throw new OverDraftError("Not enough Money");
        }
        return true;
    }
    public void subscribe(CashVaultObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(CashVaultObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (CashVaultObserver observer : observers) {
            observer.updateCash(balance);
        }
    }

}
