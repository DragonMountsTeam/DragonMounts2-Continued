package net.dragonmounts.entity.helper;

import net.dragonmounts.entity.TameableDragonEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.math.Vec3d;

import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

public class DragonMoveHelper extends EntityMoveHelper {

    private final TameableDragonEntity dragon;
    private static int YAW_SPEED = 5;

    public DragonMoveHelper(TameableDragonEntity dragon) {
        super(dragon);
        this.dragon = dragon;
        this.speed = 0.9D;
    }

    @Override
    public void onUpdateMoveHelper() {
        // original movement behavior if the entity isn't flying
        if (dragon.isFlying()) {
            Vec3d dragonPos = dragon.getPositionVector();
            Vec3d movePos = new Vec3d(posX, posY, posZ);

            // get direction vector by subtracting the current position from the
            // target position and normalizing the result
            Vec3d dir = movePos.subtract(dragonPos).normalize();

            // get euclidean distance to target
            double dist = dragonPos.distanceTo(movePos);

            // move towards target if it's far away enough   dragon.width
            if (dist > dragon.width) {
                double boost = dragon.boosting() ? 4 : 1;
                double flySpeed = dragon.getEntityAttribute(TameableDragonEntity.MOVEMENT_SPEED_AIR).getAttributeValue() * boost;

                // update velocity to approach target
                dragon.motionX = dir.x * flySpeed;
                dragon.motionY = dir.y * flySpeed;
                dragon.motionZ = dir.z * flySpeed;

            } else if (dragon.getControllingPlayer() != null) {
                // just slow down and hover at current location
                dragon.motionX *= 0.8;
                dragon.motionY *= 0.8;
                dragon.motionZ *= 0.8;

                dragon.motionY += Math.sin(dragon.ticksExisted / 5) * 0.03;
            }

            // face entity towards target
            if (dist > 2.5E-7) {
                float YAW_SPEED = dragon.getControllingPlayer() != null ? 5 : 15;
                float newYaw = (float) Math.toDegrees(Math.PI * 2 - Math.atan2(dir.x, dir.z));
                dragon.rotationYaw = limitAngle(dragon.rotationYaw, newYaw, YAW_SPEED);
                entity.setAIMoveSpeed((float) (speed * entity.getEntityAttribute(MOVEMENT_SPEED).getAttributeValue()));
            }

            // apply movement
            dragon.move(MoverType.SELF, dragon.motionX, dragon.motionY, dragon.motionZ);
        } else {
            super.onUpdateMoveHelper();
        }
    }
}
