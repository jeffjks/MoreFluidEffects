package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.JsonObject;
import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidDamageEffect extends FluidEffect {

    private final ResourceKey<DamageType> damageType;
    private final float damage;
    private final boolean damagesItems;

    public FluidDamageEffect(ResourceKey<DamageType> damageType, float damage, boolean damagesItems) {
        super(1);
        this.damageType = damageType;
        this.damage = damage;
        this.damagesItems = damagesItems;
    }

    public FluidDamageEffect(ResourceKey<DamageType> damageType, float damage, boolean damagesItems, int interval) {
        super(interval);
        this.damageType = damageType;
        this.damage = damage;
        this.damagesItems = damagesItems;
    }

    @Override
    protected void apply(Entity entity) {
        if (!damagesItems && !(entity instanceof LivingEntity))
            return;
        boolean hurt = entity.hurt(ModDamageTypes.of(entity.level(), damageType), damage);
        if (hurt) {
            entity.playSound(SoundEvents.GENERIC_BURN, 0.4F, 2.0F + entity.getRandom().nextFloat() * 0.4F);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("damageType", damageType.location().toString());
        obj.addProperty("damage", damage);
        obj.addProperty("damagesItems", damagesItems);
        return obj;
    }
}