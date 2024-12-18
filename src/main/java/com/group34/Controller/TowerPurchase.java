package com.group34.Controller;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import com.group34.Model.Board.PlacementError;
import com.group34.Model.Shop.OverDraftError;
import com.group34.Model.Shop.Shop;
import com.group34.View.BoardView;
import com.group34.View.ShopButtonComponent;

public class TowerPurchase {
    public TowerPurchase(
        List<ShopButtonComponent> buttons,
        BoardView boardView,
        Shop shop
    ) {
        // TODO: explain what this does
        for (ShopButtonComponent button : buttons) {
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

            boardView.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(dtde.getDropAction());
                    Transferable transferable = dtde.getTransferable();
                    String towerType = (String) transferable.getTransferData(DataFlavor.stringFlavor);

                    // Create and place the tower on the board
                    Point dropPoint = dtde.getLocation();
                    try {
                        shop.purchaseItem(towerType, dropPoint);
                        boardView.addTowerButton(dropPoint);
                    } catch (PlacementError e) {
                        boardView.showTemporaryMessage(e.getMessage());
                    }
                    catch (OverDraftError e) {
                        boardView.showTemporaryMessage(e.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }});
        }
    }
}
