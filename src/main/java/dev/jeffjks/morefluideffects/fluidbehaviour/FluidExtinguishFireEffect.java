package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.LivingEntity;

public class FluidExtinguishFireEffect extends FluidEffect {

    public FluidExtinguishFireEffect() {
        super(1);
    }

    @Override
    protected void apply(LivingEntity living) {
        living.extinguishFire();
    }
}
