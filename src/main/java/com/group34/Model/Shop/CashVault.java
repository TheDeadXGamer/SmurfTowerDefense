package com.group34.Model.Shop;

import java.util.ArrayList;

/**
 * A class representing the cash vault of the shop.
 * The cash vault is responsible for keeping track of the balance of the shop.
 * It also notifies observers when the balance changes.
 */
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

    /**
     * Checks if the balance is enough to buy the given amount.
     * @param amount the amount to check
     * @return true if the balance is enough, false otherwise
     * @throws OverDraftError if the balance is not enough
     */
    public boolean ableToBuy(int amount) throws OverDraftError {
        if (balance < amount) {
            throw new OverDraftError("Not enough Money");
        }
        return true;
    }

    /**
     * Subscribes an observer to the cash vault.
     * @param observer the observer to subscribe
     */
    public void subscribe(CashVaultObserver observer) {
        observers.add(observer);
    }

    /**
     * Unsubscribes an observer from the cash vault.
     * @param observer the observer to unsubscribe
     */
    public void unsubscribe(CashVaultObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers that the balance has changed.
     * @return void
     */
    private void notifyObservers() {
        for (CashVaultObserver observer : observers) {
            observer.updateCash(balance);
        }
    }

}
