package dev.jeffjks.morefluideffects;

import com.google.gson.JsonObject;
import dev.jeffjks.morefluideffects.fluidbehaviour.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class FluidEffectFactories {

    private FluidEffectFactories() {}

    private static final Map<String, Function<JsonObject, FluidEffect>> REGISTRY = new HashMap<>();

    static {
        registerFluidEffectType(FluidExplosionOnFireEffect.class.getSimpleName(), json -> {
            int explosionRadius = getInt(json, "explosionRadius", 3);
            return new FluidExplosionOnFireEffect(explosionRadius);
        });

        registerFluidEffectType(FluidFireEffect.class.getSimpleName(), json -> {
            String modeStr = getString(json, "mode", "extinguish");
            FluidFireEffect.FireMode mode = FluidFireEffect.FireMode.valueOf(modeStr.toUpperCase());
            int ticks = getInt(json, "ticks", 0);
            return new FluidFireEffect(mode, ticks);
        });

        registerFluidEffectType(FluidFreezeEffect.class.getSimpleName(), json -> {
            int frozenTicks = getInt(json, "frozenTicks", 3);
            int maxFrozenTicks = getInt(json, "maxFrozenTicks", 240);
            return new FluidFreezeEffect(frozenTicks, maxFrozenTicks);
        });

        registerFluidEffectType(FluidStatusEffect.class.getSimpleName(), json -> {
            String mobEffectId = getString(json, "mobEffectId", "");
            int duration = getInt(json, "duration", 60);
            int effectLevel = getInt(json, "effectLevel", 2);
            int interval = getInt(json, "interval", 12);
            return new FluidStatusEffect(mobEffectId, duration, effectLevel, interval);
        });

        registerFluidEffectType(FluidDamageEffect.class.getSimpleName(), json -> {
            String damageTypeId = getString(json, "damageType", "minecraft:generic");
            float damage = getFloat(json, "damage", 2.0f);
            boolean damagesItems = getBoolean(json, "damagesItems", true);
            ResourceKey<DamageType> key = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse(damageTypeId));
            int interval = getInt(json, "interval", 1);
            return new FluidDamageEffect(key, damage, damagesItems, interval);
        });

        registerFluidEffectType(FluidWaterLikeEffect.class.getSimpleName(), json -> {
            return new FluidWaterLikeEffect();
        });
    }

    public static void registerFluidEffectType(String type, Function<JsonObject, FluidEffect> factory) {
        REGISTRY.put(type, factory);
    }

    public static FluidEffect create(String type, JsonObject params) {
        Function<JsonObject, FluidEffect> fn = REGISTRY.get(type);
        if (fn == null) {
            MoreFluidEffects.LOGGER.error("Unknown effect type: {}", type);
            return null;
        }
        return fn.apply(params == null ? new JsonObject() : params);
    }

    private static boolean getBoolean(JsonObject obj, String key, boolean def) {
        return obj.has(key) ? obj.get(key).getAsBoolean() : def;
    }

    private static float getFloat(JsonObject obj, String key, float def) {
        return obj.has(key) ? obj.get(key).getAsFloat() : def;
    }

    private static int getInt(JsonObject obj, String key, int def) {
        return obj.has(key) ? obj.get(key).getAsInt() : def;
    }

    private static String getString(JsonObject obj, String key, String def) {
        return obj.has(key) ? obj.get(key).getAsString() : def;
    }
}