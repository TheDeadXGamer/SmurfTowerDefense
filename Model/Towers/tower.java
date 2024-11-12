package Towers;



public abstract class tower implements towerInterface  {


        



        
    protected int attackSpeed;
    protected int damage;
    protected tower towerType;
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

    public void attack() {
        //dont know how to implement yet, would imagine using listeners that check enemies position and then calls on this if in range
    }

    
}
