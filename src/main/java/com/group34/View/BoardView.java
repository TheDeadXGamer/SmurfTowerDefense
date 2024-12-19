package com.group34.View;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.group34.Model.Board.Board;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Game.Game;
import com.group34.Model.Projectile.Projectile;
import com.group34.Model.Tower.Tower;
import com.group34.Model.Transform.ViewportManager;

public class BoardView extends JPanel {
    private String temporaryMessage = null;
    private boolean showTemporaryMessage = false;
    private Timer errorTimer;
    public Board board;
    public Game game;
    private final ViewportManager viewportManager;

    private Map<String, Image> enemyImages = Map.of(
        "Gargamel", new ImageIcon(
            getClass().getResource(ViewConstants.GARGAMEL_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH)
    );

    private Map<String, Image> towerImages = Map.of(
        "LightningSmurf", new ImageIcon(
            getClass().getResource(ViewConstants.LIGHTNINGSMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH)
    );

    private Map<String, Image> projectileImages = Map.of(
        "LightningBolt", new ImageIcon(
            getClass().getResource(ViewConstants.LIGHTNINGBOLT_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH)
    );

    final Image backgroundImage = new ImageIcon(
        getClass().getResource(ViewConstants.BASE_MAP_IMAGE_PATH)
    ).getImage();


    public BoardView(Board board, Game game, RightPanel rightPanel) {
        this.board = board;
        this.game = game;
        this.viewportManager = new ViewportManager(board.getDimension());
        setPreferredSize(board.getDimension());
        setLayout(new BorderLayout());
        add(rightPanel, BorderLayout.EAST);

        // Add a listener to handle resizing of the window
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = getSize();
                viewportManager.handleResize(newSize);
                repaint();
            }
        });

        // Add a listener to handle mouse clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Convert screen coordinates to game coordinates
                Point2D screenPoint = new Point2D.Double(e.getX(), e.getY());
                Point2D gamePoint = viewportManager.getTransformer().toGame(screenPoint);

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Could use this for showing tower placement preview
                Point2D gamePoint = viewportManager.getTransformer().toGame(
                        new Point2D.Double(e.getX(), e.getY())
                );
                // Update preview position
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Enable anti-aliasing for smoother graphics
        if (true) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        }

        renderBackground(g);
        renderTowers(g);
        renderEnemies(g);
        renderProjectiles(g);

        // Draw temporary message if visible
        if (showTemporaryMessage) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            int messageWidth = g.getFontMetrics().stringWidth(temporaryMessage);
            g.drawString(
                    temporaryMessage,
                    ViewConstants.GAME_WIDTH/4 + 35,
                    ViewConstants.GAME_HEIGHT/2 -100
            );
        }
    }

    private void renderProjectiles(Graphics g) {
        Iterator<Projectile> iterProjectile = board.getProjectileManager().getProjectiles().iterator();
        while (iterProjectile.hasNext()) {
            Projectile p = iterProjectile.next();
            Point2D gamePos = p.getCurrentPosition();
            // Convert game position to screen position
            Point2D screenPos = viewportManager.getTransformer().toScreen(gamePos);
            int scaledSize = (int)viewportManager.getTransformer().scaleValue(ViewConstants.TOWER_SIZE);

            g.drawImage(
                    projectileImages.get(p.getClass().getSimpleName()),
                    (int) screenPos.getX() - scaledSize / 2,
                    (int) screenPos.getY() - scaledSize / 2,
                    scaledSize,
                    scaledSize,
                    this
            );
        }
    }

    private void renderEnemies(Graphics g) {
        Iterator<Enemy> iterEnemy = game.getEnemies();
        while (iterEnemy.hasNext()) {
            Enemy e = iterEnemy.next();
            Point2D gamePos = e.getPosition();
            // Convert game position to screen position
            Point2D screenPos = viewportManager.getTransformer().toScreen(gamePos);
            int scaledSize = (int)viewportManager.getTransformer().scaleValue(ViewConstants.TOWER_SIZE);
            g.drawImage(
                    enemyImages.get(e.getClass().getSimpleName()),
                    (int) screenPos.getX() - scaledSize / 2,
                    (int) screenPos.getY() - scaledSize / 2,
                    scaledSize,
                    scaledSize,
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

    private void renderTowers(Graphics g) {
        Iterator<Tower> iter = board.getTowers();
        while (iter.hasNext()) {
            Tower t = iter.next();
            Point2D gamePos = t.getPosition();
            // Convert game position to screen position
            Point2D screenPos = viewportManager.getTransformer().toScreen(gamePos);
            int scaledSize = (int)viewportManager.getTransformer().scaleValue(ViewConstants.TOWER_SIZE);

            g.drawImage(
                    towerImages.get(t.getTowerType()),
                    (int) screenPos.getX() - scaledSize / 2,
                    (int) screenPos.getY() - scaledSize / 2,
                    scaledSize,
                    scaledSize,
                    this
            );
        }
    }

    private void renderBackground(Graphics g) {
        Dimension currentSize = viewportManager.getTransformer().getCurrentResolution();
        g.drawImage(
                backgroundImage,
                0,
                0,
                (int) currentSize.getWidth(),
                (int) currentSize.getHeight(),
                this
        );
    }

    public void showTemporaryMessage(String message) {
        temporaryMessage = message;
        showTemporaryMessage = true;

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
}