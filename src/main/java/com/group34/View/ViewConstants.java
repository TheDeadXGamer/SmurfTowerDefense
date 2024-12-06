package com.group34.View;

import java.awt.Color;
import java.awt.Dimension;

public class ViewConstants {
    // colors
    public static final Color RIGHT_PANEL_COLOR = new Color(255, 201, 54); // orange
    public static final Color BORDER_COLOR = Color.BLACK; // black

    // size integers
    public static final int BUTTON_SIZE = 30;
    public static final int TOWER_SIZE = 80;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int RIGHT_PANEL_WIDTH = GAME_WIDTH - 600;

    // dimension objects
    public static final Dimension BUTTON_PANEL_SIZE = new Dimension(200, 50);
    public static final Dimension SHOP_PANEL_SIZE = new Dimension(RIGHT_PANEL_WIDTH, 600);

    // image paths
    public static final String BASE_MAP_IMAGE_PATH = "/assets/Maps/BaseMap.png";

    public static final String LIGHTNINGSMURF_IMAGE = "/assets/Towers/SmurfLightningTower.png";

    public static final String GARGAMEL_IMAGE = "/assets/Enemies/BaseEnemy.png";
}