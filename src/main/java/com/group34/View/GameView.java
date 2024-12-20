package com.group34.View;

import javax.swing.JPanel;
import java.awt.*;

/**
 * GameView class is a JPanel that contains the BoardView and RightPanel.
 */
public class GameView extends JPanel {

    public GameView(BoardView boardView,RightPanel rightPanel) {

        setLayout(new BorderLayout());
        add(boardView,BorderLayout.CENTER);
        add(rightPanel,BorderLayout.EAST);
        Dimension boardViewSize = boardView.getPreferredSize();
        Dimension rightPanelSize = rightPanel.getPreferredSize();
        setPreferredSize(new Dimension(boardViewSize.width + rightPanelSize.width, boardViewSize.height));

    }
}