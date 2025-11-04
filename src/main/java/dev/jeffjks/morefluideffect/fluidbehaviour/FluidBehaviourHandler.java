package dev.jeffjks.morefluideffect.fluidbehaviour;

import dev.jeffjks.morefluideffect.MoreFluidEffect;
import dev.jeffjks.morefluideffect.common.registry.ModDamageTypes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@EventBusSubscriber(modid = MoreFluidEffect.MODID)
public class FluidBehaviourHandler {
    private static final List<ResourceLocation> FLUID_ACID_IDS = List.of(
            ResourceLocation.fromNamespaceAndPath("mekanism", "sulfuric_acid"),
            ResourceLocation.fromNamespaceAndPath("mekanism", "hydrogen")
    );

    // 캐시: 대상 유체 타입들
    private static final Set<FluidType> FLUID_ACID_TYPES = new HashSet<>();

    private FluidBehaviourHandler() {}

    @SubscribeEvent
    public static void onServerAboutToStart(net.neoforged.neoforge.event.server.ServerAboutToStartEvent e) {
        rebuildTypes(e.getServer().registryAccess());
    }

    private static void rebuildTypes(RegistryAccess.Frozen access) {
        FLUID_ACID_TYPES.clear();
        var fluids = access.registryOrThrow(Registries.FLUID);
        for (var id : FLUID_ACID_IDS) {
            fluids.getOptional(id).ifPresent(f -> FLUID_ACID_TYPES.add(f.getFluidType()));
        }
    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof LivingEntity living)) {
            return;
        }
        final Level level = living.level();

        if (level.isClientSide())
            return;

        if (living.isAlive() == false)
            return;

        if (living.tickCount % 10 != 0)
            return;

        boolean isInAcidFluid = living.isInFluidType((type, height) -> FLUID_ACID_TYPES.contains(type), false);
        if (isInAcidFluid) {
            living.hurt(ModDamageTypes.of(living.level(), ModDamageTypes.ACID), 2f);
        }
/*
        if (isInTaggedFluid(living, FluidEffecterTags.Fluids.ACID)) {
            // 간단히 magic/poison 등 기존 소스 사용.
            // 나중에 JSON DamageType 등록해 'fluideffecter:acid' 커스텀도 가능.
            living.hurt(living.damageSources().magic(), 2.0F);
        }*/
    }
/*
    private static boolean isInTaggedFluid(LivingEntity living, TagKey<Fluid> tag) {
        if (living.isInFluidType())
        Level lvl = living.level();
    }*/
}
