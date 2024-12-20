package net.dragonmounts.entity.breath.nodes;

import net.dragonmounts.entity.TameableDragonEntity;
import net.dragonmounts.entity.breath.DragonBreathHelperP;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Created by TGG on 14/03/2016.
 * Used to generate a discrete projectile (eg the Nether dragon which fires fireballs)
 * Create a copy of the factory per dragon - the factory has a 'cooldown' to regulate how often projectiles are fired
 * 1) spawnProjectile(...) spawns a projectile entity, if the factory is ready.  Returns true if spawned.
 * 2) updateTick(...) every tick, to keep the factory synchronised (eg cooldown timer)
 */
public interface BreathProjectileFactory {
  boolean spawnProjectile(World world, TameableDragonEntity dragon, Vec3d origin, Vec3d target,
                          BreathNodeP.Power i_power);
  void updateTick(DragonBreathHelperP.BreathState breathState);
}
