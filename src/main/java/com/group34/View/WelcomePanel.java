package com.group34.View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.group34.GameState;
import com.group34.TowerDefence;
import com.group34.Model.Game.Game;
import com.group34.Model.Tower.Tower;

public class WelcomePanel extends JPanel {

    private TowerDefence towerDefence;
    
    public WelcomePanel(TowerDefence towerDefence){
        this.towerDefence = towerDefence;
        renderScreen();
    }

    public void renderScreen() {
        setLayout(new BorderLayout());

        //Title
        JLabel title = new JLabel("Welcome to Smurf Tower Defence", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 
        centerPanel.add(Box.createVerticalStrut(ViewConstants.GAME_HEIGHT/3)); 
        title.setAlignmentX(CENTER_ALIGNMENT);
        centerPanel.add(title);
        centerPanel.add(Box.createVerticalStrut(ViewConstants.GAME_HEIGHT/3));
        

        JButton playButton = new JButton("Play");

        playButton.setAlignmentX(CENTER_ALIGNMENT);
        centerPanel.add(playButton);

        add(centerPanel, BorderLayout.CENTER);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                towerDefence.setState(GameState.BETWEEN_ROUND); // Navigate to Game screen
            }
        });
    }

}
