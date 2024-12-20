package net.dragonmounts.def;

import net.dragonmounts.entity.TameableDragonEntity;
import net.dragonmounts.entity.helper.DragonLifeStage;
import net.dragonmounts.registry.DragonType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Random;

public class EnchantType extends DragonType {
    public EnchantType(ResourceLocation identifier, Properties props) {
        super(identifier, props);
    }

    @Override
    public void tick(TameableDragonEntity dragon) {
        if (dragon.getLifeStageHelper().isOldEnough(DragonLifeStage.PREJUVENILE)) {
            World level = dragon.world;
            Random random = level.rand;
            float s = dragon.getScale() * 1.2f;
            float h = dragon.height * s;
            float f = (dragon.width - 0.65F) * s;
            for (int i = -25; i < s; ++i) {
                double x = dragon.posX + (random.nextDouble() - 0.5) * f;
                double y = dragon.posY + (random.nextDouble() - 0.5) * h;
                double z = dragon.posZ + (random.nextDouble() - 0.5) * f;
                level.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x, y, z, 0, 0, 0);
            }
        }
    }
}
