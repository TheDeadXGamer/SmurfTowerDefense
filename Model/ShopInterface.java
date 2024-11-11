interface ShopInterface {
    void displayItems();  // display the towers in the shop
    boolean buyItem(String towerType, int playerMoney);  // return true if successful purchase
    int getTowerCost(String towerType);  // maybe unnecessary

}