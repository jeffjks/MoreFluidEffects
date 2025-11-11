package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.Config;
import dev.jeffjks.morefluideffects.ConfigJsonApplier;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;


@EventBusSubscriber(modid = MoreFluidEffects.MOD_ID)
public class FluidBehaviourHandler {

    private FluidBehaviourHandler() {}

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent e) {
        if (Config.ENABLE_DEFAULT_FLUID_EFFECT.isTrue()) {
            //DefaultFluidGroups.registerDefaultFluidEffects();
            ConfigJsonApplier.loadConfig();
        }
        FluidEffectsRegistry.rebuildMap(e.getServer().registryAccess());
    }

    @SubscribeEvent
    public static void onEntityTick(final EntityTickEvent.Post event) {
//        if (!(event.getEntity() instanceof LivingEntity living)) {
//            return;
//        }
        var entity = event.getEntity();

        final Level level = entity.level();

        if (level.isClientSide())
            return;

        FluidEffectsRegistry.tickFluid(entity);
    }
}
