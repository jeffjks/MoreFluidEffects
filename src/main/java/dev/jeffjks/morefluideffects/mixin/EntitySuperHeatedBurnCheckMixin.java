package dev.jeffjks.morefluideffects.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.jeffjks.morefluideffects.api.SuperHeatedExt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// Entity in super heated fluid ignore extinguish fire
@Mixin(Entity.class)
public abstract class EntitySuperHeatedBurnCheckMixin {

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
                ((SuperHeatedExt) fluidType).mfx$isSuperHeated());

        if (inSuperHeatedFluid) {
            return false; // noneMatch = false → "화상 환경으로 인식"
        }

        return original; // 기본 동작 유지
    }
}