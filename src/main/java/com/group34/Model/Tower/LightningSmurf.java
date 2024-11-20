package com.group34.Model.Tower;

import java.awt.geom.Point2D;

public class LightningSmurf extends Tower implements Upgrade {

    public LightningSmurf(Point2D position) {
        super(1,2,500,position,300);
        towerType = this.getClass().getName();
        projectileType = "LightningBolt";

    }

    @Override
    public Tower upgrade() {
        return new ThunderSmurf(this.position);
    }

}
