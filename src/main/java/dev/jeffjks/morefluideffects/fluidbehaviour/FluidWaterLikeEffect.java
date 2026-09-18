package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.JsonObject;
import com.mojang.serialization.MapCodec;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidWaterLikeEffect extends FluidEffect {
    public static final String TYPE = MoreFluidEffects.MOD_ID + ":water_like";

    public static final MapCodec<FluidWaterLikeEffect> CODEC = MapCodec.unit(FluidWaterLikeEffect::new);

    public FluidWaterLikeEffect() {
        super(TYPE, 1);
    }

    @Override
    protected void apply(Entity entity) {
        if (entity instanceof LivingEntity living) {
            if (living.isSensitiveToWater()) {
                float waterDamage = 1F;
                living.hurt(living.damageSources().drown(), waterDamage);
            }
        }
    }
}
