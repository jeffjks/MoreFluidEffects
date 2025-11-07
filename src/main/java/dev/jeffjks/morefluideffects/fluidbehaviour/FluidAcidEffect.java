package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidAcidEffect extends FluidEffect {

    private final float damage;

    public FluidAcidEffect(float damage) {
        super(1);
        this.damage = damage;
    }

    @Override
    protected void apply(Entity entity) {
        if (!(entity instanceof LivingEntity))
            return;
        entity.hurt(ModDamageTypes.of(entity.level(), ModDamageTypes.ACID), damage);
    }
}
