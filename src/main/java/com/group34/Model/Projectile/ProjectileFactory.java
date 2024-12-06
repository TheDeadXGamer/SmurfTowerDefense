package com.group34.Model.Projectile;

import com.group34.Model.Enemy.Enemy;

public abstract class ProjectileFactory {

   public abstract Projectile createProjectile(Enemy enemy);
}
