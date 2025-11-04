package dev.jeffjks.morefluideffect.datagen;

import dev.jeffjks.morefluideffect.MoreFluidEffect;
import dev.jeffjks.morefluideffect.common.registry.ModDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();

    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MoreFluidEffect.MODID));
        addDamageType();
    }

    private void addDamageType() {
        BUILDER.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
    }

}
