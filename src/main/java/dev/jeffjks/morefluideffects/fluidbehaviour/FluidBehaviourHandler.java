package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;


@EventBusSubscriber(modid = MoreFluidEffects.MODID)
public class FluidBehaviourHandler {

    private FluidBehaviourHandler() {}

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent e) {
        DefaultFluidGroups.registerFluidEffects();
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
