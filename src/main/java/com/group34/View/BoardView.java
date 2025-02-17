package com.group34.View;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import com.group34.Model.Board.Board;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Game.Game;
import com.group34.Model.Projectile.Projectile;
import com.group34.Model.Tower.Tower;

/**
 * The BoardView class is responsible for rendering the game board, including the background, towers, enemies, and projectiles.
 */
public class BoardView extends JPanel implements SellTowerListener  {
    private String temporaryMessage = null;
    private boolean showTemporaryMessage = false;
    private Timer errorTimer;
    private Board board;
    private Game game;
    public RightPanel rightPanel;
    private JPanel overlayPanel;
    private HashMap<Tower,JButton> buttons = new HashMap<>();

    // Images for enemies, towers, and projectiles
    private final Map<String, Image> enemyImages = Map.of(
        "Gargamel", new ImageIcon(
            getClass().getResource(ViewConstants.GARGAMEL_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH),
        "Azrael", new ImageIcon(
            getClass().getResource(ViewConstants.AZRAEL_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH),
        "Hogatha", new ImageIcon(
            getClass().getResource(ViewConstants.HOGATHA_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH),
        "Balthazar", new ImageIcon(
            getClass().getResource(ViewConstants.BALTHAZAR_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH)
    );

    // Images for towers
    private final Map<String, Image> towerImages = Map.of(
        "LightningSmurf", new ImageIcon(
            getClass().getResource(ViewConstants.LIGHTNINGSMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH),
        "FireSmurf", new ImageIcon(
            getClass().getResource(ViewConstants.FIRESMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH),
        "WaterSmurf", new ImageIcon(
            getClass().getResource(ViewConstants.WATERSMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH),
        "ThunderSmurf", new ImageIcon(
            getClass().getResource(ViewConstants.LIGHTNINGSMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH),
        "BlazeSmurf", new ImageIcon(
            getClass().getResource(ViewConstants.FIRESMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH),
        "TsunamiSmurf", new ImageIcon(
            getClass().getResource(ViewConstants.WATERSMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH)
    );

    // Images for projectiles
    private final Map<String, Image> projectileImages = Map.of(
        "LightningBolt", new ImageIcon(
            getClass().getResource(ViewConstants.LIGHTNINGBOLT_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH),
        "Fireball", new ImageIcon(
            getClass().getResource(ViewConstants.FIREBALL_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH),
        "Waterdrop", new ImageIcon(
            getClass().getResource(ViewConstants.WATERDROP_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH)
    );

    // Background image
    private final Image backgroundImage = new ImageIcon(
        getClass().getResource(ViewConstants.BASE_MAP_IMAGE_PATH))
        .getImage();


    public BoardView(
        Board board, 
        Game game,
        RightPanel rightPanel
    ) {
        this.board = board;
        this.game = game;
        this.rightPanel = rightPanel;

        setPreferredSize(board.getDimension());
        setLayout(new BorderLayout());
        add(rightPanel, BorderLayout.EAST);

        overlayPanel = new JPanel(null);
        overlayPanel.setOpaque(false);

        // Add a listener to the overlay panel to display the shop panel when clicked
        addMouseListener(new MouseAdapter() {
                             @Override
                             public void mouseClicked(MouseEvent e) {
                                 rightPanel.displayShopPanel();
                             }
                         });


        add(overlayPanel);

    }


    /**
     * Paints the game board, including the background, towers, enemies, and projectiles.
     * @param g The graphics object to paint with
     * @see javax.swing.JComponent#paintComponent(Graphics)
     * @return void
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        
        // Enable antialiasing for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        renderBackground(g);
        renderTowers(g);
        renderEnemies(g);
        renderProjectiles(g);

        // Draw temporary message if visible
        if (showTemporaryMessage) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString(
                    temporaryMessage,
                    ViewConstants.GAME_WIDTH/4 + 35,
                    ViewConstants.GAME_HEIGHT/2 -100
            );
        }
    }

    /**
     * Renders the projectiles on the game board.
     * @param g The graphics object to paint with
     * @return void
     */
    private void renderProjectiles(Graphics g) {
        Iterator<Projectile> iterProjectile = board.getProjectileManager().getProjectiles().iterator();

        while (iterProjectile.hasNext()) {
            Projectile p = iterProjectile.next();

            g.drawImage(
                    projectileImages.get(p.getClass().getSimpleName()),
                    (int) p.getCurrentPosition().getX() - ViewConstants.TOWER_SIZE / 2,
                    (int) p.getCurrentPosition().getY() - ViewConstants.TOWER_SIZE / 2,
                    ViewConstants.TOWER_SIZE,
                    ViewConstants.TOWER_SIZE,
                    this
            );
        }
    }

    /**
     * Renders the enemies on the game board.
     * @param g The graphics object to paint with
     * @return void
     */
    private void renderEnemies(Graphics g) {
        Iterator<Enemy> iterEnemy = game.getEnemies();

        while (iterEnemy.hasNext()) {
            Enemy e = iterEnemy.next();

            g.drawImage(
                    enemyImages.get(e.getClass().getSimpleName()),
                    (int) e.getPosition().getX() - ViewConstants.TOWER_SIZE / 2,
                    (int) e.getPosition().getY() - ViewConstants.TOWER_SIZE / 2,
                    ViewConstants.TOWER_SIZE,
                    ViewConstants.TOWER_SIZE,
                    this
            );
            g.setColor(ViewConstants.HEALTH_BAR_COLOR);
            g.setFont(g.getFont().deriveFont(20f));
            g.drawString(
                    Integer.toString(e.getHealth()),
                    (int) e.getPosition().getX() - ViewConstants.TOWER_SIZE / 2,
                    (int) e.getPosition().getY() + ViewConstants.TOWER_SIZE / 2
            );
        }
    }

    /**
     * Renders the towers on the game board.
     * @param g The graphics object to paint with
     * @return void
     */
    private void renderTowers(Graphics g) {
        Iterator<Tower> iter = board.getTowers();

        while (iter.hasNext()) {
            Tower t = iter.next();
            g.drawImage(
                    towerImages.get(t.getTowerType()),
                    (int) t.getPosition().getX() - ViewConstants.TOWER_SIZE / 2,
                    (int) t.getPosition().getY() - ViewConstants.TOWER_SIZE / 2,
                    ViewConstants.TOWER_SIZE,
                    ViewConstants.TOWER_SIZE,
                    this
            );
        }
    }

    /**
     * Renders the background image on the game board.
     * @param g The graphics object to paint with
     * @return void
     */
    private void renderBackground(Graphics g) {
        g.drawImage(
                backgroundImage,
                0,
                0,
                (int) board.getDimension().getWidth(),
                (int) board.getDimension().getHeight(),
                this
        );
    }

    /**
     * Displays a temporary message on the game board.
     * @param message The message to display
     * @return void
     */
    public void showTemporaryMessage(String message) {
        temporaryMessage = message;
        showTemporaryMessage = true;
        repaint();
        if (errorTimer == null || !errorTimer.isRunning()) {
            // Hide the message after 2 seconds
            errorTimer = new Timer(2000, e -> {
                showTemporaryMessage = false;
                repaint(); // Trigger repaint to remove the message
            });
            errorTimer.setRepeats(false);
            errorTimer.start();
        }
    }

    /**
     * Adds a tower button to the game board.
     * @param point The point to add the tower button at
     * @return void
     */
    public void addTowerButton( Point point) {
        JButton towerButton = new JButton();

        int buttonWidth = 50;  // Adjust this to your desired button width
        int buttonHeight = 70; // Adjust this to your desired button height

        // Calculate the position for the top-left corner so the button is centered
        int x = (int) point.getX();
        int y = (int) point.getY();
        int topLeftX = x - (buttonWidth / 2);
        int topLeftY = y - (buttonHeight / 2);

        Tower tower = findTower(point);
        buttons.put(tower,towerButton);
        towerButton.setBounds(topLeftX,topLeftY,buttonWidth,buttonHeight);

        towerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.displayUpgradePanel(tower);
            }
        });

        towerButton.setContentAreaFilled(false); // Removes the button's background
        towerButton.setBorderPainted(false);    // Removes the border
        towerButton.setFocusPainted(false);     // Removes focus painting
        towerButton.setOpaque(false);           // Ensures the button is fully transparent

        overlayPanel.add(towerButton);
        overlayPanel.revalidate();
        overlayPanel.repaint();

    }

    /**
     * Finds a tower at a given point.
     * @param point 
     * @return void
     */
    private Tower findTower(Point point) {
        Point2D position = new Point2D.Double(point.getX(),point.getY());
        Iterator<Tower> towers = board.getTowers();

        while (towers.hasNext()) {
            Tower tower = towers.next();
            if (tower.getPosition().distance(position) <= 1) {
                return tower;
            }
        }
        return null;
    }

    /**
     * Updates the game board when a tower is sold.
     * @param tower The tower to sell
     * @return void
     */
    @Override
    public void update(Tower tower) {
        JButton button = buttons.get(tower);
        overlayPanel.remove(button);
        buttons.remove(tower,button);
        rightPanel.displayShopPanel();
        repaint();
    }
}