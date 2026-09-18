package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.FluidEffectData;
import dev.jeffjks.morefluideffects.ModDataMaps;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.registries.datamaps.DataMapsUpdatedEvent;


@EventBusSubscriber(modid = MoreFluidEffects.MOD_ID)
public class FluidBehaviourHandler {

    private FluidBehaviourHandler() {}

    @SubscribeEvent
    public static void onDataMapsUpdated(DataMapsUpdatedEvent event) {
        event.ifRegistry(Registries.FLUID, registry -> {
            FluidEffectsRegistry.reset();

            for (Fluid fluid : registry) {
                Holder<Fluid> holder = registry.wrapAsHolder(fluid);
                FluidEffectData data = holder.getData(ModDataMaps.FLUID_EFFECTS);
                if (data == null)
                    continue;

                ResourceLocation fluidId = registry.getKey(fluid);
                FluidTypeConfigurator.configure(fluidId, fluid.getFluidType(),
                        data.canExtinguish(), data.vaporizesInUltraWarm(), data.effects());
                FluidEffectsRegistry.register(fluid.getFluidType(), data.effects());
            }
        });
    }

    @SubscribeEvent
    public static void onEntityTick(final EntityTickEvent.Post event) {
        var entity = event.getEntity();

        final Level level = entity.level();

        if (level.isClientSide())
            return;

        FluidEffectsRegistry.tickFluid(entity);
    }
}
