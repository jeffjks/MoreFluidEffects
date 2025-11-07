package dev.jeffjks.morefluideffects.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.jeffjks.morefluideffects.api.FluidTypeExt;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FluidType.class)
public abstract class FluidTypeMixin implements FluidTypeExt {
    @Shadow @Final @Mutable
    private boolean canExtinguish;

    @Override
    public void mfx$setCanExtinguish(boolean value) {
        this.canExtinguish = value;
    }

    @Unique
    private boolean mfx$superHeated;

    @Unique
    private boolean mfx$vaporizesInUltraWarm;

    @Unique
    private boolean mfx$locked;

    @Override
    public boolean mfx$isSuperHeated() { return mfx$superHeated; }

    @Override
    public void mfx$setSuperHeated(boolean value) {
        if (mfx$locked)
            throw new IllegalStateException("superHeated already locked");
        mfx$superHeated = value;
    }

    @Override
    public boolean mfx$isVaporizesInUltraWarm() { return mfx$vaporizesInUltraWarm; }

    @Override
    public void mfx$setVaporizesInUltraWarm(boolean value) {
        if (mfx$locked)
            throw new IllegalStateException("vaporizesInUltraWarm already locked");
        mfx$vaporizesInUltraWarm = value;
    }

    @Override
    public void mfx$locked() { mfx$locked = true; }


    // VaporizeOnPlacement return true if sVaporizesInUltraWarm == true
    @ModifyReturnValue(
            method = "isVaporizedOnPlacement",
            at = @At("RETURN")
    )
    private boolean mfx$modifiedIsVaporizedOnPlacement(boolean original, Level level, BlockPos pos, FluidStack stack) {
        if (!level.dimensionType().ultraWarm())
            return original; // 첫 번째 return은 유지
        FluidType self = (FluidType)(Object) this;
        return original || ((FluidTypeExt) self).mfx$isVaporizesInUltraWarm();
    }
}