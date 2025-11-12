package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidFreezeEffect extends FluidEffect {

    private final int frozenTicks;
    private final int maxFrozenTicks;

    public FluidFreezeEffect(int frozenTicks, int maxFrozenTicks) {
        super(1);
        this.frozenTicks = frozenTicks;
        this.maxFrozenTicks = maxFrozenTicks;
    }

    @Override
    protected void apply(Entity entity) {
        if (entity instanceof LivingEntity living) {
            living.setTicksFrozen(Math.min(living.getTicksFrozen() + frozenTicks + 2, maxFrozenTicks)); // -2 every tick in vanilla
        }
    }
}
