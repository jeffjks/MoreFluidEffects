package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.Config;
import dev.jeffjks.morefluideffects.FluidEffectData;
import dev.jeffjks.morefluideffects.ModDataMaps;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;


@EventBusSubscriber(modid = MoreFluidEffects.MOD_ID)
public class FluidBehaviourHandler {

    private FluidBehaviourHandler() {}

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent e) {
        /*
        if (Config.ENABLE_FLUID_EFFECTS.get()) {
            ConfigJsonApplier.loadConfig();
        }*/
        applyDataMaps(e.getServer().registryAccess());
        FluidEffectsRegistry.rebuildMap(e.getServer().registryAccess());
    }

    private static void applyDataMaps(RegistryAccess.Frozen access) {
        FluidEffectsRegistry.reset();

        var fluidRegistry = access.registryOrThrow(Registries.FLUID);
        for (Fluid fluid : fluidRegistry) {
            Holder<Fluid> holder = fluidRegistry.wrapAsHolder(fluid);
            FluidEffectData data = holder.getData(ModDataMaps.FLUID_EFFECTS);
            if (data == null)
                continue;

            ResourceLocation fluidId = fluidRegistry.getKey(fluid);
            FluidTypeConfigurator.configure(fluidId, fluid.getFluidType(),
                    data.canExtinguish(), data.vaporizesInUltraWarm(), data.effects());

            FluidEffectsRegistry.register(fluidId, data.effects());
        }
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
