package com.group34.Model.Tower;

import java.util.Iterator;
import java.util.stream.Stream;

import com.group34.Model.Enemy.BaseEnemy;

public interface Attack extends ITower {
   public void attack(Stream<BaseEnemy> enemy); 
}
