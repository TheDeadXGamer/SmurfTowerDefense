package com.group34.Model.Game;
import javax.swing.*;
import java.awt.*;


public class WelcomeScreen extends JPanel{
    public WelcomeScreen(GameController controller, CardLayout cardLayout, JPanel container){
        

        setLayout(new BorderLayout());

        //Title
        JLabel title = new JLabel("Welcome to Smurf Tower Defence", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JLabel text = new JLabel("Please choose difficulty", SwingConstants.CENTER);
        text.setFont(new Font("Arial",Font.PLAIN, 18));
        add(text);

        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 
        centerPanel.add(Box.createVerticalStrut(20)); 
        centerPanel.add(text);
        centerPanel.add(Box.createVerticalStrut(20));


        // Difficulty buttons
        JButton easyButton = new JButton("Easy");
        easyButton.addActionListener(e -> {
            controller.setDifficulty(Difficulty.EASY);
            cardLayout.show(container, "GAME_VIEW");
        });


        JButton mediumButton = new JButton("Medium");
        mediumButton.addActionListener(e -> {
            controller.setDifficulty(Difficulty.MEDIUM);
            cardLayout.show(container, "GAME_VIEW");
        });


        JButton hardButton = new JButton("Hard");
        hardButton.addActionListener(e -> {
            controller.setDifficulty(Difficulty.HARD);
            cardLayout.show(container, "GAME_VIEW");
        });


        //Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);

        centerPanel.add(buttonPanel);
        add(centerPanel, BorderLayout.CENTER);


    }
    


}
