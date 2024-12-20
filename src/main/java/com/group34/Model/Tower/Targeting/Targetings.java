package com.group34.Model.Tower.Targeting;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;

import java.util.List;

/**
 * Generic interface for targeting enemies
 * @param <enemies> List of enemies
 */
public interface Targetings<enemies extends Positionable & Attackable> {
    void attack(List<enemies> enemy);
}
