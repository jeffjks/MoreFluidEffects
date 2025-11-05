package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.world.entity.LivingEntity;

public class FluidHeatEffect extends FluidEffect {

    private final float damage;

    public FluidHeatEffect(float damage) {
        super(1);
        this.damage = damage;
    }

    @Override
    protected void apply(LivingEntity living) {
        living.hurt(ModDamageTypes.of(living.level(), ModDamageTypes.HEAT), damage);
    }
}
