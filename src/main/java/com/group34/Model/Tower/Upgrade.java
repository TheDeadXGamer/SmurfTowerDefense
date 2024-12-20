package com.group34.Model.Tower;

public interface Upgrade {

    /**
     * Upgrades a tower into its upgraded form.
     * @return the upgraded tower.
     */
    Tower upgrade();
    int getUpgradeCost();
}
