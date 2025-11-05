package dev.jeffjks.morefluideffect.fluidbehaviour;

import net.minecraft.world.entity.LivingEntity;

public class FluidWaterLikeEffect extends FluidEffect {

    private final float waterDamage = 1F;

    public FluidWaterLikeEffect() {
        super(1);
    }

    @Override
    protected void apply(LivingEntity living) {
        if (living.isSensitiveToWater()) {
            living.hurt(living.damageSources().drown(), waterDamage);
        }
    }
}
