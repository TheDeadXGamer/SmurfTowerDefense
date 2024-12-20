package com.group34.Model.Projectile;

import com.group34.Model.Positionable;

public abstract class ProjectileFactory {
   public abstract Projectile createProjectile(Positionable enemy);
}
