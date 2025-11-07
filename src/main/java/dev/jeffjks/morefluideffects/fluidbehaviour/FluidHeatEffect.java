package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class FluidHeatEffect extends FluidEffect {

    private final float damage;

    public FluidHeatEffect(float damage) {
        super(1);
        this.damage = damage;
    }

    @Override
    protected void apply(Entity entity) {
        Level level = entity.level();
        if (entity instanceof LivingEntity living) {
            living.hurt(ModDamageTypes.of(level, ModDamageTypes.HEAT), damage);
        }
    }
}
