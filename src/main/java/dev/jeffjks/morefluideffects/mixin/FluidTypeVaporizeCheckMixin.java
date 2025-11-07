package dev.jeffjks.morefluideffects.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.jeffjks.morefluideffects.api.FluidTypeExt;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FluidType.class)
public abstract class FluidTypeVaporizeCheckMixin {

    @ModifyReturnValue(
            method = "isVaporizedOnPlacement",
            at = @At("RETURN")
    )

    private boolean mfx$extendUltraWarmVaporize(boolean original, Level level, BlockPos pos, FluidStack stack) {
        if (!level.dimensionType().ultraWarm())
            return original; // 첫 번째 return 그대로
        FluidType self = (FluidType)(Object) this;
        return original || ((FluidTypeExt) self).mfx$isVaporizesInUltraWarm();
    }
}

