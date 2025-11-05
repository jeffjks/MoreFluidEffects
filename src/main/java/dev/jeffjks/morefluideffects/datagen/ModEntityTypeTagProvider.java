package dev.jeffjks.morefluideffects.datagen;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import dev.jeffjks.morefluideffects.common.registry.ModEntityTypeTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends EntityTypeTagsProvider {

    public ModEntityTypeTagProvider(PackOutput output, CompletableFuture<Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, MoreFluidEffects.MODID, existingFileHelper);
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
