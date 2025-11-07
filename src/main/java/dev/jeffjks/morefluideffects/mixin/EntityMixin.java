package dev.jeffjks.morefluideffects.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.jeffjks.morefluideffects.api.FluidTypeExt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class EntityMixin {

    // Entity in super heated fluid ignore extinguish fire
    @ModifyExpressionValue(
            method = "move",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/stream/Stream;noneMatch(Ljava/util/function/Predicate;)Z"
            )
    )
    private boolean mfx$injectSuperHeatedFluidCheck(boolean original, MoverType type, Vec3 pos) {
        Entity self = (Entity) (Object) this;

        // nonMatch(...) 의 결과가 original에 들어오고, isSuperHeatedFluid 결과와 종합해서 결과 수정
        boolean inSuperHeatedFluid = self.isInFluidType((fluidType, height) ->
                ((FluidTypeExt) fluidType).mfx$isSuperHeated());

        if (inSuperHeatedFluid) {
            return false;
        }

        return original; // 기본 동작 유지
    }


    // Entity in super heated fluid ignore fire damage
    @Redirect(
            method = "baseTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;isInLava()Z",
                    ordinal = 0
            )
    )
    private boolean mfx$redirectedIsInLava(Entity self) {
        boolean lava = self.isInLava();
        boolean superheated = self.isInFluidType((type, height) ->
                ((FluidTypeExt) type).mfx$isSuperHeated()
        );

        return lava || superheated;
    }
}