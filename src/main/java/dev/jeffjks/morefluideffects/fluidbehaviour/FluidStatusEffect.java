package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.JsonObject;
import dev.jeffjks.morefluideffects.utils.MobEffectsHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class FluidStatusEffect extends FluidEffect {

    private final String mobEffectId;
    private final int duration;
    private final int effectLevel;

    public FluidStatusEffect(String mobEffectId, int duration, int effectLevel, int interval) {
        super(interval);
        this.mobEffectId = mobEffectId;
        this.duration = duration;
        this.effectLevel = effectLevel;
    }

    @Override
    protected void apply(Entity entity) {
        Level level = entity.level();
        if (entity instanceof LivingEntity living) {
            MobEffectInstance mobEffect = MobEffectsHelper.getMobEffectInstance(level, mobEffectId, duration, effectLevel);
            if (mobEffect != null) {
                living.addEffect(mobEffect);
            }
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("mobEffectId", mobEffectId);
        obj.addProperty("duration", duration);
        obj.addProperty("effectLevel", effectLevel);
        obj.addProperty("interval", interval);
        return obj;
    }
}
