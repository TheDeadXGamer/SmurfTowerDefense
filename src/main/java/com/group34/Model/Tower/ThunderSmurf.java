package com.group34.Model.Tower;

import java.awt.geom.Point2D;

public class ThunderSmurf extends Tower{
    
    public ThunderSmurf(Point2D position) {
        super(1,3,1000,position,300);
        towerType = this.getClass().getName();
        projectileType = "ThunderBolt";
    }
}
