package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.utils.MobEffectsHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class FluidGenericEffect extends FluidEffect {

    private final String mobEffectId;
    private final int duration;
    private final int effectLevel;

    public FluidGenericEffect(int interval, String mobEffectId, int duration, int effectLevel) {
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
}
