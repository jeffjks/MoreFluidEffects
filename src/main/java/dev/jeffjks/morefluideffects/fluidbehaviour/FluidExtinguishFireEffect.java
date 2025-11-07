package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.Entity;

public class FluidExtinguishFireEffect extends FluidEffect {

    public FluidExtinguishFireEffect() {
        super(1);
    }

    @Override
    protected void apply(Entity entity) {
        entity.extinguishFire();
    }
}
