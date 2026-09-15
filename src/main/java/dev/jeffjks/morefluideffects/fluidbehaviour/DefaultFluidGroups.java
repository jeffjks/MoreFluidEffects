package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.jeffjks.morefluideffects.FluidEffectJsonData;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import dev.jeffjks.morefluideffects.api.FluidTypeExt;
import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.List;

public final class DefaultFluidGroups {
    private static final FluidEffect WATER_LIKE_EFFECT = new FluidWaterLikeEffect();

    private DefaultFluidGroups() {}

    public static FluidEffectJsonData registerDefaultFluidEffects() {
        FluidEffectJsonData cfg = new FluidEffectJsonData();

        // Mekanism
        cfg.addFluidMapping(getFluidMapping("mekanism:hydrogen", true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.CRYOGENIC, 2f),
                new FluidFreezeEffect(6, 360)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:oxygen", true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.CRYOGENIC, 2f),
                new FluidFreezeEffect(6, 360)
        )));

        cfg.addFluidMapping(getFluidMapping("mekanism:chlorine", true, true, List.of(
                new FluidStatusEffect("minecraft:poison", 12, 60, 2),
                new FluidFreezeEffect(3, 240)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:sulfur_dioxide", true, true, List.of(
                new FluidStatusEffect("minecraft:poison", 25, 60, 1)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:sulfur_trioxide", true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.ACID, 2f)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:sulfuric_acid", true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.ACID, 2f)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:hydrogen_chloride", true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.ACID, 2f)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:hydrofluoric_acid", true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.ACID, 2f),
                new FluidStatusEffect("minecraft:poison", 12, 1200, 3),
                new FluidStatusEffect("minecraft:weakness", 12, 1200, 2)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:uranium_oxide", false, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 8f),
                FluidFireEffect.ignite(300)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:uranium_hexafluoride", true, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 2f)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:ethene", false, true, List.of(
                new FluidFreezeEffect(3, 240),
                new FluidExplosionOnFireEffect(4F)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:sodium", true, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 2f)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:superheated_sodium", false, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 8f),
                FluidFireEffect.ignite(300)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:brine", true, true, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("mekanism:lithium", false, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 2f)
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
                FluidFireEffect.extend(300)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanismgenerators:deuterium", true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.CRYOGENIC, 2f),
                new FluidFreezeEffect(6, 360)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanismgenerators:fusion_fuel", true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.CRYOGENIC, 2f),
                new FluidFreezeEffect(6, 360)
        )));
        cfg.addFluidMapping(getFluidMapping("mekanismgenerators:tritium", true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.CRYOGENIC, 2f),
                new FluidFreezeEffect(6, 360)
        )));

        // Create
        cfg.addFluidMapping(getFluidMapping("create:honey", true, true));
        cfg.addFluidMapping(getFluidMapping("create:chocolate", true, true));
        cfg.addFluidMapping(getFluidMapping("createadditions:bioethanol", false, true, List.of(
                FluidFireEffect.extend(300)
        )));
        cfg.addFluidMapping(getFluidMapping("createadditions:seed_oil", false, true, List.of(
                FluidFireEffect.extend(300)
        )));

        // Ender IO
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_nutrient_distillation_still", true, true, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_dew_of_the_void_still", true, false, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_hootch_still", true, true, List.of(
                WATER_LIKE_EFFECT,
                FluidFireEffect.extend(300)
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_rocket_fuel_still", false, true, List.of(
                new FluidExplosionOnFireEffect(4F)
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_fire_water_still", false, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 4f),
                FluidFireEffect.ignite(300)
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_xp_juice_still", false, false));
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_liquid_sunshine_still", true, true, List.of(
                WATER_LIKE_EFFECT
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_liquid_darkness_still", true, false));
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_cloud_seed_still", true, true, List.of(
                WATER_LIKE_EFFECT,
                new FluidFreezeEffect(1, 140)
        )));
        cfg.addFluidMapping(getFluidMapping("enderio:fluid_cloud_seed_concentrated_still", true, true, List.of(
                WATER_LIKE_EFFECT,
                new FluidFreezeEffect(3, 240)
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
            effectType.params = effect.toJson();
            fm.effects.add(effectType);
        }

        var loc = ResourceLocation.bySeparator(id, ':');
        FluidEffectsRegistry.register(loc, effectList);

        return fm;
    }



    public static void mapFluidEffect(String effectId, boolean canExtinguish, boolean vaporizesInUltraWarm,
                                       List<FluidEffect> effectList) {
        ResourceLocation loc = ResourceLocation.bySeparator(effectId, ':');

        validateFireConsistency(loc, canExtinguish, effectList);

        var fluidType = BuiltInRegistries.FLUID.get(loc).getFluidType();
        if (fluidType.isAir()) {
            MoreFluidEffects.LOGGER.warn("No such fluid type: {}", loc);
        }
        else {
            modifyFluidProperty(fluidType, canExtinguish, vaporizesInUltraWarm);
        }

        FluidEffectsRegistry.register(loc, effectList);
    }

    private static void validateFireConsistency(ResourceLocation id, boolean canExtinguish, List<FluidEffect> effectList) {
        if (!canExtinguish || effectList == null)
            return;

        effectList.stream()
                .filter(fx -> fx instanceof FluidFireEffect)
                .map(fx -> ((FluidFireEffect) fx).getMode())
                .forEach(mode -> MoreFluidEffects.LOGGER.warn(
                        "Fluid '{}' has canExtinguish=true but also defines FluidFireEffect({}). " +
                                "The fire will be re-ignited every tick, so canExtinguish is effectively ignored.",
                        id, mode));
    }

    private static void modifyFluidProperty(FluidType fluidType, boolean canExtinguish, boolean vaporizesInUltraWarm) {
        ((FluidTypeExt) fluidType).mfx$resetFluidFlags();

        ((FluidTypeExt) fluidType).mfx$setCanExtinguish(canExtinguish);
        ((FluidTypeExt) fluidType).mfx$setVaporizesInUltraWarm(vaporizesInUltraWarm);

        ((FluidTypeExt) fluidType).mfx$locked();
    }

    private static void setSuperHeatedFieldToLava() {
        var lavaType = NeoForgeMod.LAVA_TYPE.value();
        ((FluidTypeExt) lavaType).mfx$locked();
    }
}