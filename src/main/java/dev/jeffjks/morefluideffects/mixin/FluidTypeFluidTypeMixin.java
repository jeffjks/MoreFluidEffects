package dev.jeffjks.morefluideffects.mixin;

import dev.jeffjks.morefluideffects.api.FluidTypeExt;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

// Mixin
@Mixin(FluidType.class)
public abstract class FluidTypeFluidTypeMixin implements FluidTypeExt {
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
}