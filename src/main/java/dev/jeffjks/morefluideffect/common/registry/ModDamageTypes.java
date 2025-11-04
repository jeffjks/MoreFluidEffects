package dev.jeffjks.morefluideffect.common.registry;

import dev.jeffjks.morefluideffect.MoreFluidEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> ACID = create("acid");
    public static final ResourceKey<DamageType> HEAT = create("heat");

    public static DamageSource of(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
    }

    public static DamageSource of(ResourceKey<DamageType> key, Entity directEntity) {
        return new DamageSource(directEntity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key), directEntity);
    }

    public static DamageSource of(ResourceKey<DamageType> key, Entity directEntity, Entity causingEntity) {
        return new DamageSource(causingEntity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key), directEntity, causingEntity);
    }

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(ACID, new DamageType(
            "morefluideffect.acid",
            DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
            0.0f,
            DamageEffects.BURNING
        ));
        context.register(HEAT, new DamageType(
                "morefluideffect.heat",
                DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                0.0f,
                DamageEffects.BURNING
        ));
    }

    private static ResourceKey<DamageType> create(String path) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(MoreFluidEffect.MODID, path));
    }
}
