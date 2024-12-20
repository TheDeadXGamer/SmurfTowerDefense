package com.group34.Controller;

import com.group34.Model.Board.Board;
import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.Shop;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;
import com.group34.Model.Tower.TowerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TowerSellTest {
    private TowerSell towerSell;

    private CashVault cashVault;
    private Board board;

    private Shop shop;

    private Tower tower;

    private static final double REFUND_FACTOR = 0.7;

    @BeforeEach
    public void setup() {
        cashVault = new CashVault(100);
        board = new Board(null);
        shop = new Shop(cashVault, board);

        TowerFactory towerFactory = new LightningSmurfFactory();
        tower = towerFactory.createTower(null);

        towerSell = new TowerSell(shop);
    }

    @Test
    public void testSellTowerSuccessfully() {
        int startingBalance = cashVault.getBalance();
        int towerSellCost = (int) (tower.getCost() * REFUND_FACTOR);

        towerSell.sellTower(tower);

        int newBalance = cashVault.getBalance();


        assertEquals(startingBalance + towerSellCost, newBalance);
    }
}
