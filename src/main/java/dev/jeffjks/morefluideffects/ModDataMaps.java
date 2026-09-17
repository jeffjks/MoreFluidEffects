package dev.jeffjks.morefluideffects;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public final class ModDataMaps {
    public static final DataMapType<Fluid, FluidEffectData> FLUID_EFFECTS = DataMapType.builder(
            ResourceLocation.fromNamespaceAndPath(MoreFluidEffects.MOD_ID, "fluid_effects"),
            Registries.FLUID,
            FluidEffectData.CODEC
    ).build();
}