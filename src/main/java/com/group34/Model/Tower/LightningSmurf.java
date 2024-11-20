package com.group34.Model.Tower;

import java.awt.geom.Point2D;

public class LightningSmurf extends Tower{


    //Dummy values

    public LightningSmurf(Point2D position) {
        super(1,2,500,position,300);
        towerType = this.getClass().getName();
        projectileType = "LightningBolt";

    }

}
