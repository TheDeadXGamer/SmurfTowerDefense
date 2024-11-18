package Game;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.awt.Graphics;
import java.io.InputStream;



public class GameView extends JPanel {
    private Image backgroundImage;
    private GameController controller;

    public GameView(GameController controller){
        this.controller = controller;

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        easyButton.addActionListener(e -> controller.setDifficulty(Difficulty.EASY));
        mediumButton.addActionListener(e -> controller.setDifficulty(Difficulty.MEDIUM));
        hardButton.addActionListener(e -> controller.setDifficulty(Difficulty.HARD));

        add(easyButton);
        add(mediumButton);
        add(hardButton);

        try {
            InputStream imageStream = getClass().getResourceAsStream("/assets/Maps/BaseMap.png"); //Different solution bcs new File() didnt work with relative path?
            if (imageStream == null) {
                throw new IOException("Background image not found.");
            }
            backgroundImage = ImageIO.read(imageStream);
        } catch (IOException e) {
            System.out.println("Error loading background image");
        }
        
         
        
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        }   
    }
}
