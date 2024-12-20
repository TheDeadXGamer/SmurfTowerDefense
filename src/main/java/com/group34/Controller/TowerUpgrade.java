package com.group34.Controller;

import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.OverDraftError;
import com.group34.Model.Tower.Tower;
import com.group34.View.BoardView;

public class TowerUpgrade {
    private CashVault cashVault;
    private BoardView boardView;

    /**
     * Upgrades the tower
     * @param tower the tower to be upgraded
     * @throws OverDraftError if the player does not have enough money to upgrade the tower
     * @return void
     */
    public void upgradeTower(Tower tower)  {
        try {
                cashVault.withdraw(tower.getUpgradeCost());

                if (tower.getUpgradeCost() == 0) {
                    boardView.showTemporaryMessage("Cant upgrade this tower further!");}
            tower.upgrade();
            boardView.rightPanel.displayUpgradePanel(tower);

        } catch (OverDraftError e) {
            boardView.showTemporaryMessage(e.getMessage());
        }
    }

    /**
     * Sets the cashVault
     * @param cashVault the cashVault to be set
     * @return void
     */
    public void setCashVault(CashVault cashVault) {
        this.cashVault = cashVault;
    }

    /**
     * Sets the boardView
     * @param boardView the boardView to be set
     * @return void
     */
    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }
}

