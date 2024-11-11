import java.util.HashMap;
import java.util.Map;

public class Shop implements ShopInterface {
    private Map<String, Integer> towerPrices;
    public Shop() {
        towerPrices = new HashMap<>();
        // TODO: add tower prices
        // towerPrices.put("tower A", price);  // add prices for towers like this
    }

    @Override
    public void displayItems() {
        // TODO: show towers with prices in UI with JavaFX
    }

    @Override
    public boolean buyItem(String towerType, int playerMoney) {
        // checks if towerType exists, then if player has enough money
        if (towerPrices.containsKey(towerType)){
            int cost = getTowerCost(towerType);
            if (playerMoney >= cost) {
                // TODO: subtract player's money by cost (call to Player class) and update UI
                return true;
            } else {
                // TODO: give message that player doesn't have enough money
                return false;
            }
        } else {
            // TODO: give message that towerType doesn't exist (shouldn't happen)
            return false;
        }
    }

    @Override
    public int getTowerCost(String towerType) {
        return towerPrices.get(towerType);
    }
}