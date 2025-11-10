package dev.jeffjks.morefluideffects.utils;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;

import java.util.Optional;

public final class MobEffectsHelper {

    public static MobEffectInstance getMobEffectInstance(Level level, String mobEffectId, int duration, int effectLevel) {

        var loc = ResourceLocation.bySeparator(mobEffectId, ':');
        MobEffect myEffect = BuiltInRegistries.MOB_EFFECT.get(loc);
        Optional<ResourceKey<MobEffect>> keyOptional = level.registryAccess().registry(Registries.MOB_EFFECT).get().getResourceKey(myEffect);

        if (keyOptional.isEmpty()) {
            MoreFluidEffects.LOGGER.warn("There is no such mob effect id: {}", mobEffectId);
            return null;
        }

        Holder<MobEffect> holder = level.registryAccess().registryOrThrow(Registries.MOB_EFFECT).getHolderOrThrow(keyOptional.get());

        int amplifier = effectLevel - 1;
        return new MobEffectInstance(holder, duration, amplifier);
    }
}