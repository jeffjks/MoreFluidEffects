package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import dev.jeffjks.morefluideffects.api.FluidTypeExt;
import dev.jeffjks.morefluideffects.mixin.FluidTypeCanExtinguishMixin;
import dev.jeffjks.morefluideffects.mixin.FluidTypeFluidTypeMixin;
import dev.jeffjks.morefluideffects.mixin.FluidTypeVaporizeCheckMixin;
import dev.jeffjks.morefluideffects.utils.FluidHelper;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;

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
        mapFluidEffect("mekanism", "hydrogen", true, true, List.of(
                CRYOGENIC_EFFECT
        ));
        mapFluidEffect("mekanism", "oxygen", true, true, List.of(
                CRYOGENIC_EFFECT
        ));
        mapFluidEffect("mekanism", "chlorine", true, true, List.of(
                POISON_EFFECT_2,
                FREEZE_EFFECT_2
        ));
        mapFluidEffect("mekanism", "sulfur_dioxide", true, true, List.of(
                POISON_EFFECT_1
        ));
        mapFluidEffect("mekanism", "sulfur_trioxide", true, true, List.of(
                ACID_EFFECT
        ));
        mapFluidEffect("mekanism", "sulfuric_acid", true, true, List.of(
                ACID_EFFECT
        ));
        mapFluidEffect("mekanism", "hydrogen_chloride", true, true, List.of(
                ACID_EFFECT
        ));
        mapFluidEffect("mekanism", "hydrofluoric_acid", true, true, List.of(
                ACID_EFFECT
        ));
        mapFluidEffect("mekanism", "uranium_oxide", false, false, List.of(
                SUPER_HEAT_EFFECT
        ));
        mapFluidEffect("mekanism", "uranium_hexafluoride", true, false, List.of(
                HEAT_EFFECT
        ));
        mapFluidEffect("mekanism", "ethene", false, true, List.of(
                FREEZE_EFFECT_2,
                EXPLOSION_ON_FIRE_EFFECT
        ));
        mapFluidEffect("mekanism", "sodium", true, false, List.of(
                HEAT_EFFECT
        ));
        mapFluidEffect("mekanism", "superheated_sodium", false, false, List.of(
                SUPER_HEAT_EFFECT
        ));
        mapFluidEffect("mekanism", "brine", true, true, List.of(
                WATER_LIKE_EFFECT
        ));
        mapFluidEffect("mekanism", "lithium", false, false, List.of(
                SUPER_HEAT_EFFECT
        ));
        mapFluidEffect("mekanism", "steam", true, true, List.of(
                WATER_LIKE_EFFECT
        ));
        mapFluidEffect("mekanism", "heavy_water", true, true, List.of(
                WATER_LIKE_EFFECT
        ));
        mapFluidEffect("mekanism", "nutritional_paste", true, true, null);
        mapFluidEffect("mekanismgenerators", "bioethanol", false, true, List.of(
                EXTEND_FIRE_EFFECT
        ));
        mapFluidEffect("mekanismgenerators", "deuterium", true, true, List.of(
                CRYOGENIC_EFFECT
        ));
        mapFluidEffect("mekanismgenerators", "fusion_fuel", true, true, List.of(
                CRYOGENIC_EFFECT
        ));
        mapFluidEffect("mekanismgenerators", "tritium", true, true, List.of(
                CRYOGENIC_EFFECT
        ));

        mapFluidEffect("create", "honey", true, true, null);
        mapFluidEffect("create", "chocolate", true, true, null);
        mapFluidEffect("createadditions", "seed_oil", false, true, List.of(
                EXTEND_FIRE_EFFECT
        ));

        mapFluidEffect("supplementaries", "lumisene", false, false, List.of(
                SUPER_HEAT_EFFECT
        ));

        setSuperHeatedFieldToLava();
    }

    private static void mapFluidEffect(String namespace, String path, boolean canExtinguish, boolean vaporizesInUltraWarm,
                                       List<FluidEffect> effectList) {
        var loc = ResourceLocation.fromNamespaceAndPath(namespace, path);

        var fluidType = FluidHelper.getFluidType(loc);
        if (fluidType == null) {
            MoreFluidEffects.LOGGER.info("No such fluid type: {}", loc);
        }
        else {
            modifyFluidProperty(fluidType, canExtinguish, vaporizesInUltraWarm, effectList);
        }

        FluidEffectsRegistry.register(loc, effectList);
    }

    private static void modifyFluidProperty(FluidType fluidType, boolean canExtinguish, boolean vaporizesInUltraWarm,
                                            List<FluidEffect> effectList) {
        ((FluidTypeCanExtinguishMixin) fluidType).setCanExtinguish(canExtinguish);
        ((FluidTypeExt) fluidType).mfx$setVaporizesInUltraWarm(vaporizesInUltraWarm);

        if (effectList != null && effectList.contains(SUPER_HEAT_EFFECT)) {
            ((FluidTypeExt) fluidType).mfx$setSuperHeated(true);
        }

        ((FluidTypeExt) fluidType).mfx$locked();
    }

    private static void setSuperHeatedFieldToLava() {
        var lavaType = NeoForgeMod.LAVA_TYPE.value();
        ((FluidTypeExt) lavaType).mfx$locked();
    }
}