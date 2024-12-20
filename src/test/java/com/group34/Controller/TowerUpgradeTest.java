package com.group34.Controller;

import com.group34.Model.Board.Board;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.Shop;
import com.group34.Model.Tower.*;
import com.group34.View.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.View;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TowerUpgradeTest {
    private TowerUpgrade towerUpgrade;

    private CashVault cashVault;

    private Tower tower;

    @BeforeEach
    public void setup() {
        towerUpgrade = new TowerUpgrade();

        cashVault = new CashVault(100);
        towerUpgrade.setCashVault(cashVault);

        Board board = new Board(ViewConstants.BOARD_SIZE);
        Game game = new Game();
        Shop shop = new Shop(cashVault, board);
        TowerSell towerSell = new TowerSell(shop);
        List<ShopButtonComponent> buttons = new ArrayList<>();
        ShopPanel shopPanel = new ShopPanel(buttons);
        Player player = new Player(50);

        StatusPanel statusPanel = new StatusPanel(cashVault, player);
        UpgradePanel upgradePanel = new UpgradePanel(towerUpgrade, towerSell);
        RightPanel rightPanel = new RightPanel(shopPanel, statusPanel, upgradePanel);

        BoardView boardView = new BoardView(board, game, rightPanel);
        towerUpgrade.setBoardView(boardView);

        TowerFactory towerFactory = new LightningSmurfFactory();
        tower = towerFactory.createTower(null);
    }

    @Test
    public void testUpgradeTowerSuccessfully() {
        int startingBalance = cashVault.getBalance();
        int towerUpgradeCost = tower.getCost();

        towerUpgrade.upgradeTower(tower);

        int newBalance = cashVault.getBalance();

        assertEquals("ThunderSmurf", tower.getTowerType());
        assertEquals(startingBalance - towerUpgradeCost, newBalance);
    }
}
