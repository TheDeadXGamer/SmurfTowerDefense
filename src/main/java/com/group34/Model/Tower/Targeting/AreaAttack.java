package com.group34.Model.Tower.Targeting;


import java.util.List;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Positionable;
import com.group34.Model.Tower.Attack;

public interface AreaAttack<enemies extends Positionable & Attackable> extends Targetings<enemies>  {
    @Override
    default void attack(List<enemies> enemy) {
        // TODO: some towers might want to have their attack do aoe
    }
    
}
