package Model;



interface towerInterface {






    int getAttackSpeed();
    int getDamage();
    int getTowerType();
    void setAttackSpeed(int attackSpeed);
    void setDamage(int damage);
    int setTowerType(towerInterface tower);
    int getCost();
    void setCost(int price);

}