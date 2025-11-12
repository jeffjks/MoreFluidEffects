package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.jeffjks.morefluideffects.FluidEffectJsonData;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import dev.jeffjks.morefluideffects.api.FluidTypeExt;
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
    private static final FluidEffect POISON_EFFECT_1 = new FluidGenericEffect("minecraft:poison", 25, 60, 1);
    private static final FluidEffect POISON_EFFECT_2 = new FluidGenericEffect("minecraft:poison", 12, 60, 2);
    private static final FluidEffect SUPER_HEAT_EFFECT = new FluidSuperHeatEffect(4F);
    private static final FluidEffect WATER_LIKE_EFFECT = new FluidWaterLikeEffect();

    private DefaultFluidGroups() {}

    public static FluidEffectJsonData registerDefaultFluidEffects() {
        FluidEffectJsonData cfg = new FluidEffectJsonData();

        // Mekanism
        cfg.addFluidMapping(getFluidMapping("mekanism:hydrogen", true, true, List.of(
                CRYOGENIC_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:oxygen", true, true, List.of(
                CRYOGENIC_EFFECT
        )));

        cfg.addFluidMapping(getFluidMapping("mekanism:chlorine", true, true, List.of(
                POISON_EFFECT_2,
                FREEZE_EFFECT_2
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:sulfur_dioxide", true, true, List.of(
                POISON_EFFECT_1
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:sulfur_trioxide", true, true, List.of(
                ACID_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:sulfuric_acid", true, true, List.of(
                ACID_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:hydrogen_chloride", true, true, List.of(
                ACID_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:hydrofluoric_acid", true, true, List.of(
                ACID_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:uranium_oxide", false, false, List.of(
                SUPER_HEAT_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:uranium_hexafluoride", true, false, List.of(
                HEAT_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:ethene", false, true, List.of(
                FREEZE_EFFECT_2,
                EXPLOSION_ON_FIRE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:sodium", true, false, List.of(
                HEAT_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:superheated_sodium", false, false, List.of(
                SUPER_HEAT_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:brine", true, true, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:lithium", false, false, List.of(
                SUPER_HEAT_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:steam", true, true, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:heavy_water", true, true, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:nutritional_paste", true, true, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanismgenerators:bioethanol", false, true, List.of(
                EXTEND_FIRE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanismgenerators:deuterium", true, true, List.of(
                CRYOGENIC_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanismgenerators:fusion_fuel", true, true, List.of(
                CRYOGENIC_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanismgenerators:tritium", true, true, List.of(
                CRYOGENIC_EFFECT
        )));

        // Create
        cfg.addFluidMapping(getFluidMapping("create:honey", true, true));
        cfg.addFluidMapping(getFluidMapping("create:chocolate", true, true));
        cfg.addFluidMapping(getFluidMapping("createadditions:bioethanol", false, true, List.of(
                EXTEND_FIRE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("createadditions:seed_oil", false, true, List.of(
                EXTEND_FIRE_EFFECT
        )));

        // Supplementaries
        cfg.addFluidMapping(getFluidMapping("supplementaries:lumisene", false, false, List.of(
                SUPER_HEAT_EFFECT
        )));

        // Ender IO
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_nutrient_distillation", true, true, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_dew_of_the_void", true, false, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:hootch", true, true, List.of(
                WATER_LIKE_EFFECT,
                EXTEND_FIRE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:rocket_fuel", false, true, List.of(
                EXPLOSION_ON_FIRE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:fire_water", false, false, List.of(
                SUPER_HEAT_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:xp_juice", false, false));
        cfg.addFluidMapping(getFluidMapping("enderio:liquid_sunshine", true, true, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:liquid_darkness", true, false));
        cfg.addFluidMapping(getFluidMapping("enderio:cloud_seed", true, true, List.of(
                WATER_LIKE_EFFECT,
                FREEZE_EFFECT_1
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:cloud_seed_concentrated", true, true, List.of(
                WATER_LIKE_EFFECT,
                FREEZE_EFFECT_2
        )));

        setSuperHeatedFieldToLava();

        return cfg;
    }

    private static FluidEffectJsonData.FluidMapping getFluidMapping(String id, boolean canExtinguish, boolean isVaporize) {
        FluidEffectJsonData.FluidMapping fm = new FluidEffectJsonData.FluidMapping();
        fm.id = id;
        fm.canExtinguish = canExtinguish;
        fm.vaporizesInUltraWarm = isVaporize;

        return fm;
    }

    private static FluidEffectJsonData.FluidMapping getFluidMapping(String id, boolean canExtinguish,
                                                                    boolean isVaporize, List<FluidEffect> effectList) {
        FluidEffectJsonData.FluidMapping fm = new FluidEffectJsonData.FluidMapping();
        fm.id = id;
        fm.canExtinguish = canExtinguish;
        fm.vaporizesInUltraWarm = isVaporize;

        for (var effect : effectList) {
            var effectType = new FluidEffectJsonData.FluidEffectType(effect.type);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            effectType.params = gson.toJsonTree(effect).getAsJsonObject();
            fm.effects.add(effectType);
        }

        var loc = ResourceLocation.bySeparator(id, ':');
        FluidEffectsRegistry.register(loc, effectList);

        return fm;
    }



    public static void mapFluidEffect(String effectId, boolean canExtinguish, boolean vaporizesInUltraWarm,
                                       List<FluidEffect> effectList) {
        var loc = ResourceLocation.bySeparator(effectId, ':');

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
        ((FluidTypeExt) fluidType).mfx$setCanExtinguish(canExtinguish);
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