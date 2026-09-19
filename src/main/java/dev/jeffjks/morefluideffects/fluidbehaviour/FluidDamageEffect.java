package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidDamageEffect extends FluidEffect {
    public static final String TYPE = MoreFluidEffects.MOD_ID + ":fluid_damage";

    public static final MapCodec<FluidDamageEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceKey.codec(Registries.DAMAGE_TYPE).fieldOf("damageType").forGetter(e -> e.damageType),
            Codec.FLOAT.fieldOf("damage").forGetter(e -> e.damage),
            Codec.BOOL.optionalFieldOf("damagesItems", true).forGetter(e -> e.damagesItems)
    ).apply(instance, FluidDamageEffect::new));

    private final ResourceKey<DamageType> damageType;
    private final float damage;
    private final boolean damagesItems;

    public FluidDamageEffect(ResourceKey<DamageType> damageType, float damage, boolean damagesItems) {
        super(TYPE, 1);
        this.damageType = damageType;
        this.damage = damage;
        this.damagesItems = damagesItems;
    }

    public FluidDamageEffect(ResourceKey<DamageType> damageType, float damage, boolean damagesItems, int interval) {
        super(TYPE, interval);
        this.damageType = damageType;
        this.damage = damage;
        this.damagesItems = damagesItems;
    }

    @Override
    protected void apply(Entity entity) {
        if (!damagesItems && !(entity instanceof LivingEntity))
            return;

        float finalDamage = damage;
        if (damageType == ModDamageTypes.CRYOGENIC
                && entity.getType().is(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {
            finalDamage *= 5F;
        }

        boolean hurt = entity.hurt(ModDamageTypes.of(entity.level(), damageType), finalDamage);
        if (hurt) {
            entity.playSound(SoundEvents.GENERIC_BURN, 0.4F, 2.0F + entity.getRandom().nextFloat() * 0.4F);
        }
    }
}