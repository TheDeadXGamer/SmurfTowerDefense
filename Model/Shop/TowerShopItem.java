package Shop;

public class TowerShopItem implements IShopItem {
    private String name;
    private int cost;

    public TowerShopItem(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCost() {
        return this.cost;
    }
}
