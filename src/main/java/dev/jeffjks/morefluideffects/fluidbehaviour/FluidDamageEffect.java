package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.JsonObject;
import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidDamageEffect extends FluidEffect {

    private final ResourceKey<DamageType> damageType;
    private final float damage;

    public FluidDamageEffect(ResourceKey<DamageType> damageType, float damage) {
        super(1);
        this.damageType = damageType;
        this.damage = damage;
    }

    @Override
    protected void apply(Entity entity) {
        if (!(entity instanceof LivingEntity))
            return;
        entity.hurt(ModDamageTypes.of(entity.level(), damageType), damage);
    }

    @Override
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("damageType", damageType.location().toString());
        obj.addProperty("damage", damage);
        return obj;
    }
}