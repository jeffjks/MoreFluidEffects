package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.world.entity.LivingEntity;

public class FluidAcidEffect extends FluidEffect {

    private final float damage;

    public FluidAcidEffect(float damage) {
        super(1);
        this.damage = damage;
    }

    @Override
    protected void apply(LivingEntity living) {
        living.hurt(ModDamageTypes.of(living.level(), ModDamageTypes.ACID), damage);
    }
}
