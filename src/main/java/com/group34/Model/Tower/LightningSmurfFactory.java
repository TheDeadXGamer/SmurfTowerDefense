package com.group34.Model.Tower;


import java.awt.geom.Point2D;

public class LightningSmurfFactory extends TowerFactory {

    public LightningSmurfFactory() {
        super(new LightningSmurf(null,0,0,0));
    }

    /**
     * Creates a tower object based on the type and position and returns it.
     * @param type the type of the tower to be created.
     * @param position the position of the tower to be created.
     * @return the tower object created.
     * @throws IllegalArgumentException if the tower type is invalid.
     */
    @Override
    public Tower createTower(String type, Point2D position) {
        switch (type.toLowerCase()) {
            case "lightningsmurf":
                return new LightningSmurf(position, 0, 0, 0);
            case "thundersmurf":
                return new ThunderSmurf(position, 0, 0, 0);
            default:
                throw new IllegalArgumentException("Invalid tower type");
        }
    }
}