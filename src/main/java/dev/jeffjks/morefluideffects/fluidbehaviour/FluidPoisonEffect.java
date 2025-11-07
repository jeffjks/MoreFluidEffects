package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidPoisonEffect extends FluidEffect {

    private final int duration;
    private final int level;

    public FluidPoisonEffect(int interval, int duration, int level) {
        super(interval);
        this.duration = duration;
        this.level = level;
    }

    @Override
    protected void apply(Entity entity) {
        if (entity instanceof LivingEntity living) {
            int amplifier = level - 1;
            living.addEffect(new MobEffectInstance(MobEffects.POISON, duration, amplifier));
        }
    }
}
