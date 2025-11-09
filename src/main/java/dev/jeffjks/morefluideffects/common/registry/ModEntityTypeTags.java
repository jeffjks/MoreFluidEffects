package dev.jeffjks.morefluideffects.common.registry;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeTags {

    public static final TagKey<EntityType<?>> IMMUNE_TO_ACID = create("immune_to_acid");
    public static final TagKey<EntityType<?>> IMMUNE_TO_CRYOGENIC = create("immune_to_cryogenic");

    private static TagKey<EntityType<?>> create(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MoreFluidEffects.MOD_ID, name));
    }
}
