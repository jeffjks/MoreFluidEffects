package dev.jeffjks.morefluideffect.fluidbehaviour;

import net.minecraft.world.entity.LivingEntity;

public class FluidFreezeEffect extends FluidEffect {

    private final int frozenTick;
    private final int maxFrozenTick;

    public FluidFreezeEffect(int frozenTick, int maxFrozenTick) {
        super(1);
        this.frozenTick = frozenTick;
        this.maxFrozenTick = maxFrozenTick;
    }

    @Override
    protected void apply(LivingEntity living) {
        living.setTicksFrozen(Math.min(living.getTicksFrozen() + frozenTick + 2, maxFrozenTick)); // -2 every tick in vanilla
    }
}
