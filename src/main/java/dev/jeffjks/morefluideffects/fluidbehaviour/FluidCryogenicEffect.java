package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidCryogenicEffect extends FluidEffect {

    private final float freezeDamage;
    private final int frozenTicks;
    private final int maxFrozenTicks;

    public FluidCryogenicEffect(float freezeDamage, int frozenTicks, int maxFrozenTicks) {
        super(1);
        this.freezeDamage = freezeDamage;
        this.frozenTicks = frozenTicks;
        this.maxFrozenTicks = maxFrozenTicks;
    }

    @Override
    protected void apply(Entity entity) {
        if (entity instanceof LivingEntity living) {
            living.hurt(living.damageSources().freeze(), freezeDamage);
            living.setTicksFrozen(Math.min(living.getTicksFrozen() + frozenTicks + 2, maxFrozenTicks)); // -2 every tick in vanilla
        }
    }
}
