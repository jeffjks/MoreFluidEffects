package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.JsonObject;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class FluidExplosionOnFireEffect extends FluidEffect {
    public static final String TYPE = MoreFluidEffects.MOD_ID + ":explosion_on_fire";

    private final float explosionRadius;

    public FluidExplosionOnFireEffect(float explosionRadius) {
        super(TYPE, 1);
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

    @Override
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("explosionRadius", explosionRadius);
        return obj;
    }
}
