package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;

public class FluidSuperHeatEffect extends FluidEffect {

    private final float igniteSeconds = 15F;
    private final float damage;

    public FluidSuperHeatEffect(float damage) {
        super(1);
        this.damage = damage;
    }

    @Override
    protected void apply(Entity entity) {
        if (!entity.fireImmune()) {
            entity.igniteForSeconds(igniteSeconds);
            if (entity.hurt(ModDamageTypes.of(entity.level(), ModDamageTypes.HEAT), damage)) {
                entity.playSound(SoundEvents.GENERIC_BURN, 0.4F, 2.0F + entity.getRandom().nextFloat() * 0.4F);
            }
        }
    }
}
