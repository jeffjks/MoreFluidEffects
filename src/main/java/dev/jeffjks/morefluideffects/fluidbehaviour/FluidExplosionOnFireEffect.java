package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class FluidExplosionOnFireEffect extends FluidEffect {

    private final float explosionRadius;

    public FluidExplosionOnFireEffect(float explosionRadius) {
        super(1);
        this.explosionRadius = explosionRadius;
    }

    @Override
    protected void apply(Entity entity) {
        if (entity.wasOnFire == false)
            return;

        Level level = entity.level();

        level.explode(null, Explosion.getDefaultDamageSource(level, entity), null,
                entity.getX(), entity.getY(0.0625F), entity.getZ(), explosionRadius, true, Level.ExplosionInteraction.TNT);
        entity.extinguishFire();
    }
}
