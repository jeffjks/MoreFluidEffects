package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public final class FluidEffectsRegistry {
    private static final Map<ResourceLocation, List<FluidEffect>> FLUID_IDS_MAP = new LinkedHashMap<>();
    private static final Map<FluidType, List<FluidEffect>> FLUID_TYPES_MAP = new LinkedHashMap<>();

    private FluidEffectsRegistry() {}

    // --- 등록 API ---
    public static void register(ResourceLocation fluidId, List<FluidEffect> effects) {
        if (effects == null || effects.size() == 0)
            return;
        FLUID_IDS_MAP.computeIfAbsent(fluidId, k -> new ArrayList<>()).addAll(effects);
    }
    public static void register(ResourceLocation fluidId, FluidEffect effects) {
        register(fluidId, Arrays.asList(effects));
    }

    // --- 리빌드: ID/Tag -> FluidType 매핑 만들어 캐시 ---
    public static void rebuildMap(RegistryAccess.Frozen access) {
        var fluids = access.registryOrThrow(Registries.FLUID);
        for (var id : FLUID_IDS_MAP.entrySet()) {
            fluids.getOptional(id.getKey()).ifPresent(
                    f -> FLUID_TYPES_MAP.put(f.getFluidType(), id.getValue()));
        }
    }

    // --- 적용: 엔티티가 현재 잠긴 FluidType 찾고 그 효과들 실행 ---
    public static void tickFluid(LivingEntity living) {
        FluidType currentFluidType = getFirstMatchingFluidType(living);
        if (currentFluidType == null)
            return;

        var effectList = FLUID_TYPES_MAP.get(currentFluidType);
        if (effectList == null || effectList.isEmpty())
            return;
        for (var fx : effectList) {
            fx.tick(living);
        }
    }

    private static FluidType getFirstMatchingFluidType(LivingEntity living) {
        final AtomicReference<FluidType> ref = new AtomicReference<>();
        // predicate에서 true를 반환하면 "그 유체 안에 있다"로 판정되므로, type을 캡쳐
        living.isInFluidType((type, height) -> {
            if (FLUID_TYPES_MAP.containsKey(type)) {
                ref.set(type);
                return true;
            }
            return false;
        }, false);
        return ref.get();
    }
}