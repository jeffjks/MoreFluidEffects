package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.Entity;

public abstract class FluidEffect implements IHasTickEffect {

    public transient String type;
    protected final int interval;

    protected FluidEffect(int interval) {
        this.type = getClass().getSimpleName();
        this.interval = Math.max(1, interval);
    }

    @Override
    public final void tick(Entity entity) {
        if (entity.tickCount % interval == 0)
            apply(entity);
    }

    protected abstract void apply(Entity entity);
}
