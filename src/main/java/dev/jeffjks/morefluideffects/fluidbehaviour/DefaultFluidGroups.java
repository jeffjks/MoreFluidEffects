package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import dev.jeffjks.morefluideffects.mixin.FluidTypeAccessor;
import dev.jeffjks.morefluideffects.utils.FluidHelper;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public final class DefaultFluidGroups {
    private static final FluidEffect ACID_EFFECT = new FluidAcidEffect(2F);
    private static final FluidEffect CRYOGENIC_EFFECT = new FluidCryogenicEffect(2F, 6, 360);
    private static final FluidEffect EXPLOSION_ON_FIRE_EFFECT = new FluidExplosionOnFireEffect(4F);
    private static final FluidEffect EXTEND_FIRE_EFFECT = new FluidExtendFireEffect(300);
    private static final FluidEffect EXTINGUISH_FIRE_EFFECT = new FluidExtinguishFireEffect(); // Deprecated
    private static final FluidEffect FREEZE_EFFECT_1 = new FluidFreezeEffect(1, 140);
    private static final FluidEffect FREEZE_EFFECT_2 = new FluidFreezeEffect(3, 240);
    private static final FluidEffect HEAT_EFFECT = new FluidHeatEffect(2F);
    private static final FluidEffect POISON_EFFECT_1 = new FluidPoisonEffect(25, 60, 1);
    private static final FluidEffect POISON_EFFECT_2 = new FluidPoisonEffect(12, 60, 2);
    private static final FluidEffect SUPER_HEAT_EFFECT = new FluidSuperHeatEffect(4F);
    private static final FluidEffect WATER_LIKE_EFFECT = new FluidWaterLikeEffect();

    private DefaultFluidGroups() {}

    public static void registerFluidEffects() {
        mapFluidEffect("mekanism", "hydrogen", true, List.of(
                CRYOGENIC_EFFECT
        ));
        mapFluidEffect("mekanism", "oxygen", true, List.of(
                CRYOGENIC_EFFECT
        ));
        mapFluidEffect("mekanism", "chlorine", true, List.of(
                POISON_EFFECT_2,
                FREEZE_EFFECT_2
        ));
        mapFluidEffect("mekanism", "sulfur_dioxide", true, List.of(
                POISON_EFFECT_1
        ));
        mapFluidEffect("mekanism", "sulfur_trioxide", true, List.of(
                ACID_EFFECT
        ));
        mapFluidEffect("mekanism", "sulfuric_acid", true, List.of(
                ACID_EFFECT
        ));
        mapFluidEffect("mekanism", "hydrogen_chloride", true, List.of(
                ACID_EFFECT
        ));
        mapFluidEffect("mekanism", "hydrofluoric_acid", true, List.of(
                ACID_EFFECT
        ));
        mapFluidEffect("mekanism", "uranium_oxide", List.of(
                SUPER_HEAT_EFFECT
        ));
        mapFluidEffect("mekanism", "uranium_hexafluoride", true, List.of(
                HEAT_EFFECT
        ));
        mapFluidEffect("mekanism", "ethene", List.of(
                FREEZE_EFFECT_2,
                EXPLOSION_ON_FIRE_EFFECT
        ));
        mapFluidEffect("mekanism", "sodium", true, List.of(
                HEAT_EFFECT
        ));
        mapFluidEffect("mekanism", "superheated_sodium", List.of(
                SUPER_HEAT_EFFECT
        ));
        mapFluidEffect("mekanism", "brine", true, List.of(
                WATER_LIKE_EFFECT
        ));
        mapFluidEffect("mekanism", "lithium", List.of(
                SUPER_HEAT_EFFECT
        ));
        mapFluidEffect("mekanism", "steam", true, List.of(
                WATER_LIKE_EFFECT
        ));
        mapFluidEffect("mekanism", "heavy_water", true, List.of(
                WATER_LIKE_EFFECT
        ));
        mapFluidEffect("mekanism", "nutritional_paste", true, null);
        mapFluidEffect("mekanismgenerators", "bioethanol", List.of(
                EXTEND_FIRE_EFFECT
        ));
        mapFluidEffect("mekanismgenerators", "deuterium", true, List.of(
                CRYOGENIC_EFFECT
        ));
        mapFluidEffect("mekanismgenerators", "fusion_fuel", true, List.of(
                CRYOGENIC_EFFECT
        ));
        mapFluidEffect("mekanismgenerators", "tritium", true, List.of(
                CRYOGENIC_EFFECT
        ));

        mapFluidEffect("create", "honey", true, null);
        mapFluidEffect("create", "chocolate", true, null);
        mapFluidEffect("createadditions", "seed_oil", List.of(
                EXTEND_FIRE_EFFECT
        ));

        mapFluidEffect("supplementaries", "lumisene", List.of(
                SUPER_HEAT_EFFECT
        ));
    }

    private static void mapFluidEffect(String namespace, String path, List<FluidEffect> effectList) {
        FluidEffectsRegistry.register(ResourceLocation.fromNamespaceAndPath(namespace, path), effectList);
    }

    private static void mapFluidEffect(ResourceLocation loc, List<FluidEffect> effectList) {
        FluidEffectsRegistry.register(loc, effectList);
    }

    private static void mapFluidEffect(String namespace, String path, boolean canExtinguish, List<FluidEffect> effectList) {
        var loc = ResourceLocation.fromNamespaceAndPath(namespace, path);
        modifyFluidProperty(loc, canExtinguish);
        mapFluidEffect(loc, effectList);
    }

    private static void modifyFluidProperty(ResourceLocation loc, boolean canExtinguish) {
        var fluidType = FluidHelper.getFluidType(loc);
        if (fluidType == null) {
            MoreFluidEffects.LOGGER.warn("No such fluid type: {}", loc);
            return;
        }
        ((FluidTypeAccessor) fluidType).setCanExtinguish(canExtinguish);
    }
}