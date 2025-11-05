package dev.jeffjks.morefluideffect.fluidbehaviour;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public final class DefaultFluidGroups {
    private DefaultFluidGroups() {}

    public static void registerFluidEffects() {
        registerFluid("mekanism", "hydrogen", List.of(
                new FluidCryogenicEffect(),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanism", "oxygen", List.of(
                new FluidCryogenicEffect(),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanism", "chlorine", List.of(
                new FluidPoisonEffect(12, 60, 1),
                new FluidFreezeEffect(2, 160),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanism", "sulfur_dioxide", List.of(
                new FluidPoisonEffect(25, 60, 0),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanism", "sulfur_trioxide", List.of(
                new FluidAcidEffect(2f),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanism", "sulfuric_acid", List.of(
                new FluidAcidEffect(2f),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanism", "hydrogen_chloride", List.of(
                new FluidAcidEffect(2f),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanism", "hydrofluoric_acid", List.of(
                new FluidAcidEffect(2f),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanism", "uranium_oxide", List.of(
                new FluidSuperHeatEffect(4f)
        ));
        registerFluid("mekanism", "uranium_hexafluoride", List.of(
                new FluidHeatEffect(2f),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanism", "ethene", List.of(
                new FluidFreezeEffect(3, 240),
                new FluidExplosionOnFireEffect()
        ));
        registerFluid("mekanism", "sodium", List.of(
                new FluidHeatEffect(2f),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanism", "superheated_sodium", List.of(
                new FluidSuperHeatEffect(4f)
        ));
        registerFluid("mekanism", "brine", List.of(
                new FluidExtinguishFireEffect(),
                new FluidWaterLikeEffect()
        ));
        registerFluid("mekanism", "lithium", List.of(
                new FluidSuperHeatEffect(4f)
        ));
        registerFluid("mekanism", "steam", List.of(
                new FluidExtinguishFireEffect(),
                new FluidWaterLikeEffect()
        ));
        registerFluid("mekanism", "heavy_water", List.of(
                new FluidExtinguishFireEffect(),
                new FluidWaterLikeEffect()
        ));
        registerFluid("mekanism", "nutritional_paste", List.of(
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanismgenerators", "bioethanol", List.of(
                new FluidExtendFireEffect(300)
        ));
        registerFluid("mekanismgenerators", "deuterium", List.of(
                new FluidCryogenicEffect(),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanismgenerators", "fusion_fuel", List.of(
                new FluidCryogenicEffect(),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("mekanismgenerators", "tritium", List.of(
                new FluidCryogenicEffect(),
                new FluidExtinguishFireEffect()
        ));
        registerFluid("createadditions", "seed_oil", List.of(
                new FluidExtendFireEffect(300)
        ));
    }

    private static void registerFluid(String namespace, String path, List<FluidEffect> effectList) {
        FluidEffectsRegistry.register(ResourceLocation.fromNamespaceAndPath(namespace, path), effectList);
    }
}