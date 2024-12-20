package com.group34.Model;

import com.group34.Model.Board.Board;
import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.Shop;
import com.group34.Model.Shop.ShopItem;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.View.ViewConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {
    private Shop shop;

    @BeforeEach
    public void setup() {
        CashVault cashVault = new CashVault(100);
        Board board = new Board(ViewConstants.BOARD_SIZE);
        shop = new Shop(cashVault, board);
    }

    @Test
    public void testAddItem() {
        ShopItem shopItem = new ShopItem(new LightningSmurfFactory(), 10);
        shop.addItem(shopItem);
        assertEquals(shop.getItems().next(), shopItem);
    }

    @Test
    public void testPurchaseItem() {
        ShopItem shopItem = new ShopItem(new LightningSmurfFactory(), 10);
        shop.addItem(shopItem);
        try {
            shop.purchaseItem(shopItem.getName(), new Point(0, 0));
        } catch (Exception e) {
            fail("Purchase failed");
        }
    }

}
