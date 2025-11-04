package dev.jeffjks.morefluideffect.fluidbehaviour;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class FluidIdGroup {
    private static final java.util.List<FluidIdGroup> ALL_GROUPS = new ArrayList<>();

    public final FluidEffect fluidEffect;
    public final List<ResourceLocation> ids;
    public final Set<FluidType> types = new HashSet<>();

    public FluidIdGroup(FluidEffect fluidEffect, List<ResourceLocation> ids) {
        this.fluidEffect = fluidEffect;
        this.ids = List.copyOf(ids);
        ALL_GROUPS.add(this);
    }

    private void rebuildTypes(RegistryAccess.Frozen access) {
        types.clear();
        var fluids = access.registryOrThrow(Registries.FLUID);
        for (var id : ids) {
            fluids.getOptional(id).ifPresent(f -> types.add(f.getFluidType()));
        }
    }

    public static void rebuildAll(RegistryAccess.Frozen access) {
        for (var g : ALL_GROUPS) {
            g.rebuildTypes(access);
        }
    }

    public static void tickAll(LivingEntity living) {
        for (var g: ALL_GROUPS) {
            if (g.isInFluid(living) == false)
                continue;
            if (living.tickCount % g.fluidEffect.interval != 0)
                continue;
            g.fluidEffect.apply(living);
        }
    }

    public boolean isInFluid(LivingEntity living) {
        return living.isInFluidType((type, height) -> types.contains(type), false);
    }
}