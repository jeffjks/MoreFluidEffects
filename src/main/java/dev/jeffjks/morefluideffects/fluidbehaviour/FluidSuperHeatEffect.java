package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.world.entity.LivingEntity;

public class FluidSuperHeatEffect extends FluidEffect {

    private final float igniteSeconds = 15F;
    private final float damage;

    public FluidSuperHeatEffect(float damage) {
        super(1);
        this.damage = damage;
    }

    @Override
    protected void apply(LivingEntity living) {
        if (!living.fireImmune())
            living.igniteForSeconds(igniteSeconds);
        living.hurt(ModDamageTypes.of(living.level(), ModDamageTypes.HEAT), damage);
    }
}
