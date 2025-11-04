package dev.jeffjks.morefluideffect.fluidbehaviour;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public final class DefaultFluidGroups {
    private DefaultFluidGroups() {}

    public static void registerFluidGroups() {
        new FluidIdGroup(new FluidAcidEffect(2f), List.of(
                ResourceLocation.fromNamespaceAndPath("mekanism", "sulfuric_acid"),
                ResourceLocation.fromNamespaceAndPath("mekanism", "hydrogen_chloride"),
                ResourceLocation.fromNamespaceAndPath("mekanism", "hydrofluoric_acid"),
                ResourceLocation.fromNamespaceAndPath("mekanism", "sulfur_trioxide")
        ));

        new FluidIdGroup(new FluidPoisonEffect(25, 60, 0), List.of(
                ResourceLocation.fromNamespaceAndPath("mekanism", "sulfur_dioxide")
        ));

        new FluidIdGroup(new FluidPoisonEffect(12, 60, 1), List.of(
                ResourceLocation.fromNamespaceAndPath("mekanism", "chlorine")
        ));

        new FluidIdGroup(new FluidFreezeEffect(2, 160), List.of(
                ResourceLocation.fromNamespaceAndPath("mekanism", "chlorine"),
                ResourceLocation.fromNamespaceAndPath("mekanism", "ethene")
        ));

        new FluidIdGroup(new FluidCryogenicEffect(), List.of(
                ResourceLocation.fromNamespaceAndPath("mekanism", "hydrogen"),
                ResourceLocation.fromNamespaceAndPath("mekanism", "oxygen"),
                ResourceLocation.fromNamespaceAndPath("mekanismgenerators", "deuterium"),
                ResourceLocation.fromNamespaceAndPath("mekanismgenerators", "fusion_fuel"),
                ResourceLocation.fromNamespaceAndPath("mekanismgenerators", "tritium")
        ));

        new FluidIdGroup(new FluidSuperHeatEffect(4f), List.of(
                ResourceLocation.fromNamespaceAndPath("mekanism", "uranium_oxide"),
                ResourceLocation.fromNamespaceAndPath("mekanism", "superheated_sodium"),
                ResourceLocation.fromNamespaceAndPath("mekanism", "lithium")
        ));

        new FluidIdGroup(new FluidHeatEffect(2f), List.of(
                ResourceLocation.fromNamespaceAndPath("mekanism", "uranium_hexafluoride"),
                ResourceLocation.fromNamespaceAndPath("mekanism", "sodium")
        ));
    }
}