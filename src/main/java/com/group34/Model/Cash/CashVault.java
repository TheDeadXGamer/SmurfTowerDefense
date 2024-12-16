package com.group34.Model.Cash;

import com.group34.Model.IObservable;
import com.group34.Model.IObserver;

import java.util.ArrayList;

public class CashVault implements IObservable {
    private ArrayList<IObserver> observers;
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
    public void reduce(int amount) throws OverDraftError {
        if (balance < amount) {
            throw new OverDraftError("Not enough Money");
        }
        balance -= amount;
        notifyObservers();
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }

}
