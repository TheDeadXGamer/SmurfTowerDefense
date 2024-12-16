package com.group34.Model.Game;

import com.group34.Model.Cash.CashVault;
import com.group34.Model.IObservable;
import com.group34.Model.IObserver;

import java.util.ArrayList;

public class StatusModel implements IObserver, IObservable {
    private CashVault cashVault;
    private Player player;
    private Game game;

    private int health;
    private int cashBalance;
    private int roundNumber;

    private ArrayList<IObserver> observers = new ArrayList<>();

    public StatusModel(CashVault cashVault, Player player, Game game) {
        this.cashVault = cashVault;
        this.player = player;
        this.game = game;
        update();

        // register as an observer for different observables
        cashVault.addObserver(this);
        player.addObserver(this);
        game.addObserver(this);
    }

    public int getHealth() {
        return health;
    }

    public int getBalance() {
        return cashBalance;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    @Override
    public void update() {
        health = player.getHealth();
        cashBalance = cashVault.getBalance();
        roundNumber = game.getRoundNumber();
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
