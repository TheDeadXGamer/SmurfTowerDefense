package com.group34.Model.Tower.Targeting;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Positionable;

import java.util.List;

public interface Targetings<enemies extends Positionable & Attackable> {
    void attack(List<enemies> enemy);
}
