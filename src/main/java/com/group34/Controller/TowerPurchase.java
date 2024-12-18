package com.group34.Controller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import com.group34.Model.Shop.Shop;
import com.group34.View.ShopButton;

public class TowerPurchase {
    public TowerPurchase(
        List<ShopButton> buttons,        
        JPanel boardView, 
        Shop shop
    ) {
        for (ShopButton button : buttons) {
            button.setTransferHandler(new TransferHandler("text") {
                @Override
                protected Transferable createTransferable(JComponent c) {
                    return new StringSelection(button.getItem().getName());
                }
                
                @Override
                public int getSourceActions(JComponent c) {
                    return COPY;
                }
            });

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    JComponent comp = (JComponent) e.getSource();
                    TransferHandler handler = comp.getTransferHandler();
                    handler.exportAsDrag(comp, (java.awt.event.InputEvent) e, TransferHandler.COPY);
                }
            });

        // boardView.setDropTarget(new DropTarget() {
        //     @Override
        //     public synchronized void drop(DropTargetDropEvent dtde) {
        //         try {
        //             dtde.acceptDrop(dtde.getDropAction());
        //             Transferable transferable = dtde.getTransferable();
        //             String towerType = (String) transferable.getTransferData(DataFlavor.stringFlavor);

        //             // Create and place the tower on the board
        //             Point dropPoint = dtde.getLocation();
        //             String checkPurchase = shop.purchaseTower(towerType, dropPoint);

        //             switch(checkPurchase) {

        //                 case "PlacedOnAnotherTower":
        //                     showTemporaryMessage("Cannot place on another tower!");
        //                     break;
        //                 case "NotEnoughMoney"  :
        //                     showTemporaryMessage("Not enough money!");
        //             }
        //         } catch (Exception e) {
        //             e.printStackTrace();
        //         }
        //     }});
        }
    }

    // private void showTemporaryMessage(String message) {
    //     temporaryMessage = message;
    //     showTemporaryMessage = true;

    //     if (errorTimer == null || !errorTimer.isRunning()) {
    //         // Hide the message after 2 seconds
    //         errorTimer = new Timer(2000, e -> {
    //             showTemporaryMessage = false;
    //             repaint(); // Trigger repaint to remove the message
    //         });
    //         errorTimer.setRepeats(false);
    //         errorTimer.start();
    //     }
    // }
    
}
