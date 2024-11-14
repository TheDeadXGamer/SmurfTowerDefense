package Tower;

import javafx.geometry.Point2D;

public class LightningSmurf extends Tower{



    public LightningSmurf(Point2D position) {
        super(1,2,500,position,300);
        towerType = this.getClass().getName();
    }

}
