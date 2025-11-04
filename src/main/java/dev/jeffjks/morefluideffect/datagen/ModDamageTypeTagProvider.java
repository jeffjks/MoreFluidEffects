package dev.jeffjks.morefluideffect.datagen;

import dev.jeffjks.morefluideffect.MoreFluidEffect;
import dev.jeffjks.morefluideffect.utils.MoreFluidEffectDamageTypes;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagProvider extends DamageTypeTagsProvider {

    public ModDamageTypeTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MoreFluidEffect.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        tag(DamageTypeTags.BYPASSES_SHIELD)
                .add(MoreFluidEffectDamageTypes.ACID);

        tag(DamageTypeTags.NO_KNOCKBACK)
                .add(MoreFluidEffectDamageTypes.ACID);

        tag(DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES)
                .add(MoreFluidEffectDamageTypes.ACID);
    }
}
