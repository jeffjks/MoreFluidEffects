package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.LivingEntity;

public class FluidCryogenicEffect extends FluidEffect {

    private final int FrozenTick = 6;
    private final int MaxFrozenTick = 360;

    public FluidCryogenicEffect() {
        super(1);
    }

    @Override
    protected void apply(LivingEntity living) {
        living.hurt(living.damageSources().freeze(), 2F);
        living.setTicksFrozen(Math.min(living.getTicksFrozen() + FrozenTick + 2, MaxFrozenTick)); // -2 every tick in vanilla
    }
}
