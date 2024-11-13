package Towers;



public abstract class Tower implements TowerInterface  {


        



        
    protected int attackSpeed;
    protected int damage;
    protected Tower towerType;
    protected int cost;
    protected int range;
        //maybe also a nr of projectiles variable?
    

        //Dont know how to nicely store upgrades for towers yet




    @Override
    public int getAttackSpeed() {
        
        return attackSpeed;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public Tower getTowerType() {
        return towerType;
    }


    private void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed; 
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }


    private void setTowerType Tower towerType) {
        this.towerType = towerType;
    }

    @Override
    public int getCost() {
        
        return cost;
    }

    private void setCost(int price) {
        this.cost = price;
        
    }

    public void attack() {
        //dont know how to implement yet, would imagine using listeners that check enemies position and then calls on this if in range
    }

    
}
