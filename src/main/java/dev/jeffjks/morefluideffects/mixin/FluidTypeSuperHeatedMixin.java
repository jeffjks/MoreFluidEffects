package dev.jeffjks.morefluideffects.mixin;

import dev.jeffjks.morefluideffects.api.SuperHeatedExt;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

// Mixin
@Mixin(FluidType.class)
public abstract class FluidTypeSuperHeatedMixin implements SuperHeatedExt {
    @Unique
    private boolean mfx$superHeated;

    @Unique
    private boolean mfx$locked;

    @Override
    public boolean mfx$isSuperHeated() { return mfx$superHeated; }

    @Override
    public void mfx$setSuperHeated(boolean v) {
        if (mfx$locked) throw new IllegalStateException("superHeated already locked");
        mfx$superHeated = v;
    }

    @Override
    public void mfx$lockSuperHeated() { mfx$locked = true; }
}