package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.Entity;

public class FluidFireEffect extends FluidEffect {

    public enum FireMode { EXTINGUISH, EXTEND, IGNITE }

    private final FireMode mode;
    private final float value; // EXTINGUISH: 안 씀 / EXTEND: fireTicks / IGNITE: igniteSeconds

    public FluidFireEffect(FireMode mode, float value) {
        super(1);
        this.mode = mode;
        this.value = value;
    }

    public static FluidFireEffect extinguish() {
        return new FluidFireEffect(FireMode.EXTINGUISH, 0);
    }

    public static FluidFireEffect extend(int fireTicks) {
        return new FluidFireEffect(FireMode.EXTEND, fireTicks);
    }

    public static FluidFireEffect ignite(float igniteSeconds) {
        return new FluidFireEffect(FireMode.IGNITE, igniteSeconds);
    }

    public FireMode getMode() {
        return mode;
    }

    @Override
    protected void apply(Entity entity) {
        switch (mode) {
            case EXTINGUISH -> {
                if (entity.isOnFire())
                    entity.clearFire();
            }
            case EXTEND -> {
                if (entity.getRemainingFireTicks() > 0)
                    entity.igniteForTicks((int) value);
            }
            case IGNITE -> entity.igniteForSeconds(value);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();

        String modeName = mode.name().toLowerCase();
        modeName = Character.toUpperCase(modeName.charAt(0)) + modeName.substring(1);

        obj.addProperty("mode", modeName);
        obj.addProperty("interval", interval);
        return obj;
    }
}