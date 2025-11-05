package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class FluidPoisonEffect extends FluidEffect {

    private final int duration;
    private final int amplifier;

    public FluidPoisonEffect(int interval, int duration, int amplifier) {
        super(interval);
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    protected void apply(LivingEntity living) {
        living.addEffect(new MobEffectInstance(MobEffects.POISON, duration, amplifier));
    }
}
