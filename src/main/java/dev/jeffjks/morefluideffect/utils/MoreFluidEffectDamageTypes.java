package dev.jeffjks.morefluideffect.utils;

import dev.jeffjks.morefluideffect.MoreFluidEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class MoreFluidEffectDamageTypes {
    public static final ResourceKey<DamageType> ACID =
            ResourceKey.create(Registries.DAMAGE_TYPE,
                    ResourceLocation.fromNamespaceAndPath(MoreFluidEffect.MODID, "acid"));

    public static DamageSource acid(Level level) {
        var holder = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ACID);
        return new DamageSource(holder);
    }
}
