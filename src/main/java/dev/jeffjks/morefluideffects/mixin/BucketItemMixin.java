package dev.jeffjks.morefluideffects.mixin;

import dev.jeffjks.morefluideffects.api.FluidTypeExt;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    // Prevent call overridden(modded) isVaporizedOnPlacement
    @Redirect(
            method = "emptyContents(Lnet/minecraft/world/entity/player/Player;" +
                    "Lnet/minecraft/world/level/Level;" +
                    "Lnet/minecraft/core/BlockPos;" +
                    "Lnet/minecraft/world/phys/BlockHitResult;" +
                    "Lnet/minecraft/world/item/ItemStack;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/neoforged/neoforge/fluids/FluidType;isVaporizedOnPlacement(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/neoforged/neoforge/fluids/FluidStack;)Z"
            )
    )
    private boolean mfx$redirectedIsVaporizedOnPlacement(FluidType self, Level level, BlockPos pos, FluidStack stack) {
        boolean orig = self.isVaporizedOnPlacement(level, pos, stack);
        boolean extra = level.dimensionType().ultraWarm()
                && ((FluidTypeExt) self).mfx$isVaporizesInUltraWarm();
        return orig || extra;
    }
}