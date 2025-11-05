package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.LivingEntity;

public class FluidExtendFireEffect extends FluidEffect {

    private final int fireTicks;

    public FluidExtendFireEffect(int fireTicks) {
        super(1);
        this.fireTicks = fireTicks;
    }

    @Override
    protected void apply(LivingEntity living) {
        if (0 < living.getRemainingFireTicks()) {
            living.igniteForTicks(fireTicks);
        }
    }
}
