package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.world.entity.LivingEntity;

public class FluidSuperHeatEffect extends FluidEffect {

    private final int fireTicks = 300;
    private final float damage;

    public FluidSuperHeatEffect(float damage) {
        super(1);
        this.damage = damage;
    }

    @Override
    protected void apply(LivingEntity living) {
        living.igniteForTicks(fireTicks);
        living.hurt(ModDamageTypes.of(living.level(), ModDamageTypes.HEAT), damage);
    }

    //@Override
    //protected void apply(ItemEntity itemEntity) {
    //    itemEntity.setRemainingFireTicks(fireTicks);
    //}
}
