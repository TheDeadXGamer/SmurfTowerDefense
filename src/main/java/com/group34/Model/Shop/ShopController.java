package com.group34.Model.Shop;

public class ShopController {
    private ShopModel shopModel;

    public ShopController(ShopModel shopModel) {
        this.shopModel = shopModel;
    }

    public ShopModel getShopModel() {
        return shopModel;
    }

    public boolean purchaseItem(IShopItem item) {
        return shopModel.purchaseItem(item);
    }
}
