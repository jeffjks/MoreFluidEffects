package dev.jeffjks.morefluideffect.datagen;

import dev.jeffjks.morefluideffect.MoreFluidEffect;
import dev.jeffjks.morefluideffect.common.registry.ModEntityTypeTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends EntityTypeTagsProvider {

    public ModEntityTypeTagProvider(PackOutput output, CompletableFuture<Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, MoreFluidEffect.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        tag(ModEntityTypeTags.IMMUNE_TO_ACID)
                .add(EntityType.WITHER)
                .add(EntityType.WARDEN);

        tag(ModEntityTypeTags.IMMUNE_TO_CRYOGENIC)
                .add(EntityType.SNOW_GOLEM)
                .add(EntityType.WITHER)
                .add(EntityType.WARDEN);
    }
}
