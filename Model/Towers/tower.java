package Model.Towers;



public abstract class tower implements towerInterface  {

    protected int attackSpeed;
    protected int damage;
    protected tower towerType;
    protected int cost;
    

    


    @Override
    public int getAttackSpeed() {
        
        return attackSpeed;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public tower getTowerType() {
        return towerType;
    }


    private void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed; 
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }


    private void setTowerType(tower tower) {
        this.towerType = tower;
    }

    @Override
    public int getCost() {
        
        return cost;
    }

    private void setCost(int price) {
        this.cost = price;
        
    }

    
}
