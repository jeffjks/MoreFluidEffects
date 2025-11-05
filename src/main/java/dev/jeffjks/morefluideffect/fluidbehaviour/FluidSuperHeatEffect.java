package dev.jeffjks.morefluideffect.fluidbehaviour;

import dev.jeffjks.morefluideffect.common.registry.ModDamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.material.LavaFluid;

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

    @Override
    protected void apply(ItemEntity itemEntity) {
        itemEntity.setRemainingFireTicks(fireTicks);
    }
}
