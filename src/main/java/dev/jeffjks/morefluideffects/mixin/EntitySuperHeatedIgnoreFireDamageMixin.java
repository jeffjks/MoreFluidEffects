package dev.jeffjks.morefluideffects.mixin;

import dev.jeffjks.morefluideffects.api.FluidTypeExt;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// Entity in super heated fluid ignore fire damage
@Mixin(Entity.class)
public abstract class EntitySuperHeatedIgnoreFireDamageMixin {

    @Redirect(
            method = "baseTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;isInLava()Z",
                    ordinal = 0
            )
    )
    private boolean mfx$redirectIsInLava(Entity self) {
        boolean lava = self.isInLava();
        boolean superheated = self.isInFluidType((type, height) ->
                ((FluidTypeExt) type).mfx$isSuperHeated()
        );

        return lava || superheated;
    }
}