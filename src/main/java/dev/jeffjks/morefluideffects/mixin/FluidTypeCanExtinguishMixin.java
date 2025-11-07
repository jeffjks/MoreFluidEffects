package dev.jeffjks.morefluideffects.mixin;

import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = FluidType.class, remap = false)
public interface FluidTypeCanExtinguishMixin {
    @Accessor("canExtinguish") @Final
    @Mutable
    void setCanExtinguish(boolean value);
}