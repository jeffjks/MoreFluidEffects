package dev.jeffjks.morefluideffects.utils;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class FluidHelper {

    public static FluidType getFluidType(String namespace, String path) {
        ResourceLocation loc = ResourceLocation.fromNamespaceAndPath(namespace, path); // 1.20.5+
        return getFluidType(loc);
    }

    public static FluidType getFluidType(ResourceLocation loc) {
        FluidType fluidType = NeoForgeRegistries.FLUID_TYPES.get(loc);
        return fluidType;
    }
}