package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidWaterLikeEffect extends FluidEffect {

    public FluidWaterLikeEffect() {
        super(1);
    }

    @Override
    protected void apply(Entity entity) {
        if (entity instanceof LivingEntity living) {
            if (living.isSensitiveToWater()) {
                float waterDamage = 1F;
                living.hurt(living.damageSources().drown(), waterDamage);
            }
        }
    }
}
