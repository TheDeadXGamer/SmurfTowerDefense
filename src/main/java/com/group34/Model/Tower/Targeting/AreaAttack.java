package com.group34.Model.Tower.Targeting;


import java.util.List;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Tower.Attack;

public interface AreaAttack extends Targetings  {
    @Override
    default void attack(List<Enemy> enemy) {

        //TODO some towers might want to have their attack do aoe
    }
    
}
