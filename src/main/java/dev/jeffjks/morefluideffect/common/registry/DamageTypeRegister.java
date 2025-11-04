package dev.jeffjks.morefluideffect.common.registry;

import dev.jeffjks.morefluideffect.utils.MoreFluidEffectDamageTypes;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;

public class DamageTypeRegister {

    private DamageTypeRegister() {}

    public static void bootstrap(BootstrapContext<DamageType> ctx) {
        ctx.register(MoreFluidEffectDamageTypes.ACID,
            new DamageType(
                "morefluideffect.acid",
                DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                0.0f,
                DamageEffects.BURNING
            ));
    }
}