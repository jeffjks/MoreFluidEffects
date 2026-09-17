package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import dev.jeffjks.morefluideffects.api.FluidTypeExt;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.List;

public final class FluidTypeConfigurator {
    private FluidTypeConfigurator() {}

    public static void configure(ResourceLocation fluidId, FluidType fluidType,
                                 boolean canExtinguish, boolean vaporizesInUltraWarm,
                                 List<FluidEffect> effects) {
        validateFireConsistency(fluidId, canExtinguish, effects);

        ((FluidTypeExt) fluidType).mfx$resetFluidFlags();
        ((FluidTypeExt) fluidType).mfx$setCanExtinguish(canExtinguish);
        ((FluidTypeExt) fluidType).mfx$setVaporizesInUltraWarm(vaporizesInUltraWarm);
        ((FluidTypeExt) fluidType).mfx$locked();
    }

    private static void validateFireConsistency(ResourceLocation id, boolean canExtinguish, List<FluidEffect> effects) {
        if (!canExtinguish || effects == null)
            return;

        effects.stream()
                .filter(fx -> fx instanceof FluidFireEffect)
                .map(fx -> ((FluidFireEffect) fx).getMode())
                .forEach(mode -> MoreFluidEffects.LOGGER.warn(
                        "Fluid '{}' has canExtinguish=true but also defines FluidFireEffect({}). " +
                                "The fire will be re-ignited every tick, so canExtinguish is effectively ignored.",
                        id, mode));
    }
}