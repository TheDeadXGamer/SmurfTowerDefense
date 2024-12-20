package com.group34.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * ViewConstants class contains constants used in the view package.
 */
public class ViewConstants {
    // colors
    public static final Color RIGHT_PANEL_COLOR = new Color(255, 201, 54); // orange
    public static final Color BORDER_COLOR = Color.BLACK; // black
    public static final Color HEALTH_BAR_COLOR = new Color(0, 255, 0); // green


    // size integers
    public static final int BUTTON_SIZE = 30;
    public static final int TOWER_SIZE = 80;
    public static final int GAME_WIDTH = 786;
    public static final int GAME_HEIGHT = 635;
    public static final int RIGHT_PANEL_WIDTH = GAME_WIDTH - 600;
    public static final int STATUS_ICON_SIZE = 30;

    // dimension objects
    public static final Dimension BUTTON_PANEL_SIZE = new Dimension(RIGHT_PANEL_WIDTH, 50);
    public static final Dimension STATUS_PANEL_SIZE = new Dimension(RIGHT_PANEL_WIDTH, 100);
    public static final Dimension SHOP_PANEL_SIZE = new Dimension(RIGHT_PANEL_WIDTH, 400);

    // image paths
    public static final String BASE_MAP_IMAGE_PATH = "/assets/Maps/BaseMap.png";
    
    public static final String SETTINGS_ICON_PATH = "/assets/Miscellaneous/settings-icon.png";
    public static final String FASTFORWARD_ICON_PATH = "/assets/Miscellaneous/fastforward-icon.png";
    public static final String HEALTH_ICON_PATH = "/assets/Other/Smurfhealth.png";
    public static final String COIN_ICON_PATH = "/assets/Other/Smurfcoins.png";

    public static final String LIGHTNINGBOLT_IMAGE = "/assets/Projectiles/LightningBolt.png";
    public static final String LIGHTNINGSMURF_IMAGE = "/assets/Towers/LightningSmurf.png";

    public static final String FIREBALL_IMAGE = "/assets/Projectiles/Fireball.png";
    public static final String FIRESMURF_IMAGE = "/assets/Towers/FireSmurf.png";

    public static final String WATERDROP_IMAGE = "/assets/Projectiles/Waterdrop.png";
    public static final String WATERSMURF_IMAGE = "/assets/Towers/WaterSmurf.png";

    public static final String GARGAMEL_IMAGE = "/assets/Enemies/BaseEnemy.png";
    public static final String AZRAEL_IMAGE = "/assets/Enemies/Azrael.png";
    public static final String BALTHAZAR_IMAGE = "/assets/Enemies/Balthazar.png";
    public static final String HOGATHA_IMAGE = "/assets/Enemies/Hogatha.png";


    /**
     *  Creates a placeholder icon, a red square.
     * @return a placeholder icon.
     **/
    public static Icon createPlaceholderIcon() {
        int width = ViewConstants.TOWER_SIZE;
        int height = ViewConstants.TOWER_SIZE;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, width, height);
        g2.dispose();
        return new ImageIcon(image);
    }
}