package com.group34.Model.Board;

public class PlacementError extends Exception {
    
    public PlacementError(String message) {
        super(message);
    }

    public PlacementError() {
        super("Invalid Placement");
    }
    
}
