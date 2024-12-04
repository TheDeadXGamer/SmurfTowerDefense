package com.group34.Model.Shop;

public class ShopController {
    private ShopModel shopModel;

    public ShopController(ShopModel shopModel) {
        this.shopModel = shopModel;
    }

    /**
     * Returns the shop model.
     * @return the shop model.
     */
    public ShopModel getShopModel() {
        return shopModel;
    }

    /**
     * Purchases an item from the shop.
     * @param item the item to be purchased.
     * @return true if the item was purchased successfully, false otherwise.
     */
    public boolean purchaseItem(IShopItem item) {
        return shopModel.purchaseItem(item);
    }
}
