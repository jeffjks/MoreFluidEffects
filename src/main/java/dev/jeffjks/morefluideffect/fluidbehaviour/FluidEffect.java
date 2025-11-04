package dev.jeffjks.morefluideffect.fluidbehaviour;

import net.minecraft.world.entity.LivingEntity;

public abstract class FluidEffect implements IHasTickEffect {
    protected final int interval; // 예: 10틱마다 실행

    protected FluidEffect(int interval) {
        this.interval = Math.max(1, interval);
    }

    @Override
    public final void tick(LivingEntity living) {
        if (living.tickCount % interval == 0)
            apply(living);
    }

    protected abstract void apply(LivingEntity living);
}
