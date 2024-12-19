package com.group34.Controller;

import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.OverDraftError;
import com.group34.Model.Tower.Tower;
import com.group34.View.BoardView;

public class TowerUpgrade {
    private CashVault cashVault;
    private BoardView boardView;

    public void upgradeTower(Tower tower)  {
        try {
            Tower upgradedTower = tower.upgrade();
            if (upgradedTower != null) {
                cashVault.withdraw(upgradedTower.getCost());
            } else {
                boardView.showTemporaryMessage("Cant upgrade this tower further!");
            }
            boardView.rightPanel.displayUpgradePanel(tower);
        } catch (OverDraftError e) {
            boardView.showTemporaryMessage(e.getMessage());
        }
    }

    public void setCashVault(CashVault cashVault) {
        this.cashVault = cashVault;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }
}

