package dev.jeffjks.morefluideffect.fluidbehaviour;

import dev.jeffjks.morefluideffect.MoreFluidEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;


@EventBusSubscriber(modid = MoreFluidEffect.MODID)
public class FluidBehaviourHandler {

    private FluidBehaviourHandler() {}

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent e) {
        DefaultFluidGroups.registerFluidEffects();
        FluidEffectsRegistry.rebuildMap(e.getServer().registryAccess());
    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof LivingEntity living)) {
            return;
        }
        final Level level = living.level();

        if (level.isClientSide())
            return;

        if (living.isAlive() == false)
            return;

        FluidEffectsRegistry.tickFluid(living);
    }
}
