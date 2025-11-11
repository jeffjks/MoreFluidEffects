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
        register("FluidAcidEffect", json -> {
            float dmg = getFloat(json, "damage", 2.0f);
            return new FluidAcidEffect(dmg);
        });

        register("FluidCryogenicEffect", json -> {
            float freeze_damage = getFloat(json, "freeze_damage", 2.0f);
            int frozen_tick = getInt(json, "frozen_tick", 6);
            int max_frozen_tick = getInt(json, "max_frozen_tick", 360);
            return new FluidCryogenicEffect(freeze_damage, frozen_tick, max_frozen_tick);
        });

        register("FluidGenericEffect", json -> {
            String effectId = getString(json, "effect_id", "");
            int interval = getInt(json, "interval", 12);
            int duration = getInt(json, "duration", 60);
            int effect_level = getInt(json, "effect_level", 2);
            return new FluidGenericEffect(effectId, interval, duration, effect_level);
        });

        register("FluidFreezeEffect", json -> {
            int frozen_tick = getInt(json, "frozen_tick", 3);
            int max_frozen_tick = getInt(json, "max_frozen_tick", 240);
            return new FluidFreezeEffect(frozen_tick, max_frozen_tick);
        });
    }

    public static void register(String type, Function<JsonObject, FluidEffect> factory) {
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