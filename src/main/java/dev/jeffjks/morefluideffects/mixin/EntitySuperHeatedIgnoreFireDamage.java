package dev.jeffjks.morefluideffects.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// Entity in super heated fluid ignore fire damage
@Mixin(Entity.class)
public abstract class EntitySuperHeatedIgnoreFireDamage {

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
                ((dev.jeffjks.morefluideffects.api.SuperHeatedExt)(Object) type).mfx$isSuperHeated()
        );

        return lava || superheated;
    }
}