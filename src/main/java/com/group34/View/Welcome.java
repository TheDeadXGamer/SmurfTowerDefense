package com.group34.View;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Welcome extends JPanel {
    
    public Welcome() {
        
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

    }
}
