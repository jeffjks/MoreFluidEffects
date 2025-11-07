package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.Entity;

public class FluidExtendFireEffect extends FluidEffect {

    private final int fireTicks;

    public FluidExtendFireEffect(int fireTicks) {
        super(1);
        this.fireTicks = fireTicks;
    }

    @Override
    protected void apply(Entity entity) {
        if (0 < entity.getRemainingFireTicks()) {
            entity.igniteForTicks(fireTicks);
        }
    }
}
