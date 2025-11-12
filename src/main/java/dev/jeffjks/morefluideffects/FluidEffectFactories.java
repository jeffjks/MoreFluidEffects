package dev.jeffjks.morefluideffects;

import com.google.gson.JsonObject;
import dev.jeffjks.morefluideffects.fluidbehaviour.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class FluidEffectFactories {

    private FluidEffectFactories() {}

    private static final Map<String, Function<JsonObject, FluidEffect>> REGISTRY = new HashMap<>();

    static {
        registerFluidEffectType(FluidAcidEffect.class.getSimpleName(), json -> {
            float damage = getFloat(json, "damage", 2.0f);
            return new FluidAcidEffect(damage);
        });

        registerFluidEffectType(FluidCryogenicEffect.class.getSimpleName(), json -> {
            float freezeDamage = getFloat(json, "freezeDamage", 2.0f);
            int frozenTicks = getInt(json, "frozenTicks", 6);
            int maxFrozenTicks = getInt(json, "maxFrozenTicks", 360);
            return new FluidCryogenicEffect(freezeDamage, frozenTicks, maxFrozenTicks);
        });

        registerFluidEffectType(FluidExplosionOnFireEffect.class.getSimpleName(), json -> {
            int explosionRadius = getInt(json, "explosionRadius", 3);
            return new FluidExplosionOnFireEffect(explosionRadius);
        });

        registerFluidEffectType(FluidExtendFireEffect.class.getSimpleName(), json -> {
            int fireTicks = getInt(json, "fireTicks", 300);
            return new FluidExtendFireEffect(fireTicks);
        });

        registerFluidEffectType(FluidFreezeEffect.class.getSimpleName(), json -> {
            int frozenTicks = getInt(json, "frozenTicks", 3);
            int maxFrozenTicks = getInt(json, "maxFrozenTicks", 240);
            return new FluidFreezeEffect(frozenTicks, maxFrozenTicks);
        });

        registerFluidEffectType(FluidGenericEffect.class.getSimpleName(), json -> {
            String mobEffectId = getString(json, "mobEffectId", "");
            int interval = getInt(json, "interval", 12);
            int duration = getInt(json, "duration", 60);
            int effectLevel = getInt(json, "effectLevel", 2);
            return new FluidGenericEffect(mobEffectId, interval, duration, effectLevel);
        });

        registerFluidEffectType(FluidHeatEffect.class.getSimpleName(), json -> {
            float damage = getFloat(json, "damage", 2.0f);
            return new FluidHeatEffect(damage);
        });

        registerFluidEffectType(FluidSuperHeatEffect.class.getSimpleName(), json -> {
            float damage = getFloat(json, "damage", 4.0f);
            return new FluidSuperHeatEffect(damage);
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