package dev.jeffjks.morefluideffects.fluidbehaviour;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public final class FluidEffectsRegistry {
    private static final Map<ResourceLocation, List<FluidEffect>> FLUID_IDS_MAP = new LinkedHashMap<>();
    private static final Map<FluidType, List<FluidEffect>> FLUID_TYPES_MAP = new LinkedHashMap<>();

    private FluidEffectsRegistry() {}

    public static void reset() {
        FLUID_TYPES_MAP.clear();
    }

    public static void register(FluidType fluidType, List<FluidEffect> effects) {
        if (effects == null || effects.isEmpty())
            return;
        FLUID_TYPES_MAP.put(fluidType, effects);
    }

    // --- 적용: 엔티티가 현재 잠긴 FluidType 찾고 그 효과들 실행 ---
    public static void tickFluid(Entity entity) {
        FluidType currentFluidType = getFirstMatchingFluidType(entity);
        if (currentFluidType == null)
            return;

        var effectList = FLUID_TYPES_MAP.get(currentFluidType);
        if (effectList == null || effectList.isEmpty())
            return;
        for (var fx : effectList) {
            fx.tick(entity);
        }
    }

    private static FluidType getFirstMatchingFluidType(Entity entity) {
        final AtomicReference<FluidType> ref = new AtomicReference<>();
        // predicate에서 true를 반환하면 "그 유체 안에 있다"로 판정되므로, type을 캡쳐
        entity.isInFluidType((type, height) -> {
            if (FLUID_TYPES_MAP.containsKey(type)) {
                ref.set(type);
                return true;
            }
            return false;
        }, false);
        return ref.get();
    }

    public static boolean hasEffectOfType(Entity entity, Class<? extends FluidEffect> effectClass) {
        FluidType type = getFirstMatchingFluidType(entity);
        if (type == null)
            return false;
        var effects = FLUID_TYPES_MAP.get(type);
        return effects != null && effects.stream().anyMatch(effectClass::isInstance);
    }
}