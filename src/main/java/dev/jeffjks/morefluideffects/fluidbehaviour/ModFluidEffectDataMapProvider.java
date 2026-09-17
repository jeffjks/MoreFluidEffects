package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.FluidEffectData;
import dev.jeffjks.morefluideffects.ModDataMaps;
import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.data.DataMapProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModFluidEffectDataMapProvider extends DataMapProvider {
    public ModFluidEffectDataMapProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void gather(@NotNull HolderLookup.Provider provider) {
        var builder = this.builder(ModDataMaps.FLUID_EFFECTS);

        // Mekanism
        builder.add(fluidKey("mekanism:hydrogen"), new FluidEffectData(true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.CRYOGENIC, 2f, false),
                new FluidFreezeEffect(6, 360)
        )), false);

        builder.add(fluidKey("mekanism:oxygen"), new FluidEffectData(true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.CRYOGENIC, 2f, false),
                new FluidFreezeEffect(6, 360)
        )), false);

        builder.add(fluidKey("mekanism:chlorine"), new FluidEffectData(true, true, List.of(
                new FluidStatusEffect(mobEffectKey("minecraft:poison"), 12, 60, 1),
                new FluidFreezeEffect(3, 240)
        )), false);

        builder.add(fluidKey("mekanism:sulfur_dioxide"), new FluidEffectData(true, true, List.of(
                new FluidStatusEffect(mobEffectKey("minecraft:poison"), 25, 60, 1)
        )), false);

        builder.add(fluidKey("mekanism:sulfur_trioxide"), new FluidEffectData(true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.ACID, 2f, false)
        )), false);

        builder.add(fluidKey("mekanism:sulfuric_acid"), new FluidEffectData(true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.ACID, 2f, false)
        )), false);

        builder.add(fluidKey("mekanism:hydrogen_chloride"), new FluidEffectData(true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.ACID, 2f, false)
        )), false);

        builder.add(fluidKey("mekanism:hydrofluoric_acid"), new FluidEffectData(true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.ACID, 2f, false),
                new FluidStatusEffect(mobEffectKey("minecraft:poison"), 12, 1200, 3),
                new FluidStatusEffect(mobEffectKey("minecraft:weakness"), 12, 1200, 2)
        )), false);

        builder.add(fluidKey("mekanism:uranium_oxide"), new FluidEffectData(false, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 8f, true),
                FluidFireEffect.ignite(300)
        )), false);

        builder.add(fluidKey("mekanism:uranium_hexafluoride"), new FluidEffectData(true, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 2f, false)
        )), false);

        builder.add(fluidKey("mekanism:ethene"), new FluidEffectData(false, true, List.of(
                new FluidFreezeEffect(3, 240),
                new FluidExplosionOnFireEffect(4F)
        )), false);

        builder.add(fluidKey("mekanism:sodium"), new FluidEffectData(true, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 2f, false)
        )), false);

        builder.add(fluidKey("mekanism:superheated_sodium"), new FluidEffectData(false, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 8f, true),
                FluidFireEffect.ignite(300)
        )), false);

        builder.add(fluidKey("mekanism:brine"), new FluidEffectData(true, true, List.of(
                new FluidWaterLikeEffect()
        )), false);
        builder.add(fluidKey("mekanism:lithium"), new FluidEffectData(false, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 2f, false)
        )), false);
        builder.add(fluidKey("mekanism:steam"), new FluidEffectData(true, true, List.of(
                new FluidWaterLikeEffect()
        )), false);
        builder.add(fluidKey("mekanism:heavy_water"), new FluidEffectData(true, true, List.of(
                new FluidWaterLikeEffect()
        )), false);
        builder.add(fluidKey("mekanism:nutritional_paste"), new FluidEffectData(true, true, List.of(
                new FluidWaterLikeEffect()
        )), false);

        // Mekanism Generators
        builder.add(fluidKey("mekanismgenerators:bioethanol"), new FluidEffectData(false, true, List.of(
                FluidFireEffect.extend(300)
        )), false);
        builder.add(fluidKey("mekanismgenerators:deuterium"), new FluidEffectData(true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.CRYOGENIC, 2f, false),
                new FluidFreezeEffect(6, 360)
        )), false);
        builder.add(fluidKey("mekanismgenerators:fusion_fuel"), new FluidEffectData(true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.CRYOGENIC, 2f, false),
                new FluidFreezeEffect(6, 360)
        )), false);
        builder.add(fluidKey("mekanismgenerators:tritium"), new FluidEffectData(true, true, List.of(
                new FluidDamageEffect(ModDamageTypes.CRYOGENIC, 2f, false),
                new FluidFreezeEffect(6, 360)
        )), false);

        // Create
        builder.add(fluidKey("create:honey"), new FluidEffectData(true, true), false);
        builder.add(fluidKey("create:chocolate"), new FluidEffectData(true, true), false);

        builder.add(fluidKey("createadditions:bioethanol"), new FluidEffectData(false, true, List.of(
                FluidFireEffect.extend(300)
        )), false);
        builder.add(fluidKey("createadditions:seed_oil"), new FluidEffectData(false, true, List.of(
                FluidFireEffect.extend(300)
        )), false);

        // Ender IO
        builder.add(fluidKey("enderio:fluid_nutrient_distillation_still"), new FluidEffectData(true, true, List.of(
                new FluidWaterLikeEffect()
        )), false);
        builder.add(fluidKey("enderio:fluid_dew_of_the_void_still"), new FluidEffectData(true, false, List.of(
                new FluidWaterLikeEffect()
        )), false);
        builder.add(fluidKey("enderio:fluid_hootch_still"), new FluidEffectData(true, true, List.of(
                new FluidWaterLikeEffect(),
                FluidFireEffect.extend(300)
        )), false);
        builder.add(fluidKey("enderio:fluid_rocket_fuel_still"), new FluidEffectData(false, true, List.of(
                new FluidExplosionOnFireEffect(4F)
        )), false);
        builder.add(fluidKey("enderio:fluid_fire_water_still"), new FluidEffectData(false, false, List.of(
                new FluidDamageEffect(ModDamageTypes.HEAT, 4f, true),
                FluidFireEffect.ignite(300)
        )), false);
        builder.add(fluidKey("enderio:fluid_xp_juice_still"), new FluidEffectData(false, false), false);
        builder.add(fluidKey("enderio:fluid_liquid_sunshine_still"), new FluidEffectData(true, true, List.of(
                new FluidWaterLikeEffect()
        )), false);
        builder.add(fluidKey("enderio:fluid_liquid_darkness_still"), new FluidEffectData(true, false), false);
        builder.add(fluidKey("enderio:fluid_cloud_seed_still"), new FluidEffectData(true, true, List.of(
                new FluidWaterLikeEffect(),
                new FluidFreezeEffect(1, 140)
        )), false);
        builder.add(fluidKey("enderio:fluid_cloud_seed_concentrated_still"), new FluidEffectData(true, true, List.of(
                new FluidWaterLikeEffect(),
                new FluidFreezeEffect(3, 240)
        )), false);
    }

    private ResourceKey<Fluid> fluidKey(String id) {
        return ResourceKey.create(Registries.FLUID, ResourceLocation.parse(id));
    }

    private ResourceKey<MobEffect> mobEffectKey(String id) {
        return ResourceKey.create(Registries.MOB_EFFECT, ResourceLocation.parse(id));
    }
}