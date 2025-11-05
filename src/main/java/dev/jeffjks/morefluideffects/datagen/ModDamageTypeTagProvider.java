package dev.jeffjks.morefluideffects.datagen;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagProvider extends DamageTypeTagsProvider {

    public ModDamageTypeTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MoreFluidEffects.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        tag(DamageTypeTags.BYPASSES_SHIELD)
                .add(ModDamageTypes.ACID)
                .add(ModDamageTypes.HEAT);

        tag(DamageTypeTags.NO_KNOCKBACK)
                .add(ModDamageTypes.ACID)
                .add(ModDamageTypes.HEAT);

        tag(DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES)
                .add(ModDamageTypes.ACID)
                .add(ModDamageTypes.HEAT);

        tag(DamageTypeTags.IS_FIRE)
                .add(ModDamageTypes.HEAT);
    }
}
