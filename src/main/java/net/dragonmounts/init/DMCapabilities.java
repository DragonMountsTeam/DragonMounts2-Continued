package net.dragonmounts.init;

import net.dragonmounts.capability.ArmorEffectManager;
import net.dragonmounts.capability.IArmorEffectManager;
import net.dragonmounts.capability.IHardShears;
import net.dragonmounts.util.DummyStorage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.dragonmounts.DragonMounts.makeId;

public class DMCapabilities {
    public static final ResourceLocation ARMOR_EFFECT_MANAGER_ID = makeId("armor_effect_manager");

    @CapabilityInject(IArmorEffectManager.class)
    public static final Capability<IArmorEffectManager> ARMOR_EFFECT_MANAGER = null;

    @CapabilityInject(IHardShears.class)
    public static final Capability<IHardShears> HARD_SHEARS = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IArmorEffectManager.class, new ArmorEffectManager.Storage(), () -> null);
        CapabilityManager.INSTANCE.register(IHardShears.class, new DummyStorage<>(), () -> null);
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof EntityPlayer) {
            event.addCapability(ARMOR_EFFECT_MANAGER_ID, new ArmorEffectManager.LazyProvider((EntityPlayer) entity));
        }
    }
}
