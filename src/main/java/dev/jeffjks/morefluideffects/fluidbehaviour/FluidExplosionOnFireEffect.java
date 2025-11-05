package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class FluidExplosionOnFireEffect extends FluidEffect {

    public FluidExplosionOnFireEffect() {
        super(1);
    }

    @Override
    protected void apply(LivingEntity living) {
        if (living.wasOnFire) {
            Level level = living.level();

            level.explode(null, Explosion.getDefaultDamageSource(level, living), null,
                    living.getX(), living.getY(0.0625F), living.getZ(), 4.0F, true, Level.ExplosionInteraction.TNT);
            living.extinguishFire();
        }
    }
}
