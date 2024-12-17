package com.group34.Controller.Shop;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;
import javax.swing.TransferHandler;

import com.group34.Model.Shop.ShopItem;
import com.group34.View.ViewConstants;

public class ShopItemComponent extends JComponent {
    private IShopItem shopItem;

    public ShopItemComponent(IShopItem shopItem) {
        this.shopItem = shopItem;
        setToolTipText(shopItem.getToolTipText());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JToolTip tooltip = createToolTip();
                tooltip.setTipText(shopItem.getToolTipText());
                ToolTipManager.sharedInstance().registerComponent(ShopItemComponent.this);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ToolTipManager.sharedInstance().unregisterComponent(ShopItemComponent.this);
            }
        });
    }

   /**
     * @param item The item to create a JComponent for.
     * @return A ShopItemComponent, a specific JComponent, for the item.
     **/
    private ShopItemComponent getTowerComponent(IShopItem item) {
        ShopItemComponent itemComponent = new ShopItemComponent(item);
        itemComponent.setLayout(new FlowLayout());

        JLabel itemImageLabel = new JLabel();
        Image towerImage = smurfImage; // TODO: make this work for different tower images
        if (towerImage != null) {
            Image image = towerImage.getScaledInstance(ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH);
            itemImageLabel.setIcon(new ImageIcon(image));
        } else {
            itemImageLabel.setIcon(ViewConstants.createPlaceholderIcon());
        }
        itemImageLabel.setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
        itemComponent.add(itemImageLabel);

        itemComponent.setTransferHandler(new TransferHandler("text") {
            @Override
            protected Transferable createTransferable(JComponent c) {
                return new StringSelection(item.getTypeName());
            }

            @Override
            public int getSourceActions(JComponent c) {
                return COPY;
            }
        });

        itemComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent comp = (JComponent) e.getSource();
                TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, e, TransferHandler.COPY);
            }
        });

        return itemComponent;
    }
}