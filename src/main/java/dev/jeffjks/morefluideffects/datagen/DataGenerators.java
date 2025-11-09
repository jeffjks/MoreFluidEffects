package dev.jeffjks.morefluideffects.datagen;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import dev.jeffjks.morefluideffects.common.registry.ModDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = MoreFluidEffects.MOD_ID)
public class DataGenerators {

    private DataGenerators() {
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        var builder = new RegistrySetBuilder()
                .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
        var builtin = new DatapackBuiltinEntriesProvider(
                packOutput, lookupProvider, builder, Set.of(MoreFluidEffects.MOD_ID)
        );
        generator.addProvider(event.includeServer(), builtin);

        generator.addProvider(event.includeServer(), new ModDamageTypeTagProvider(packOutput, builtin.getRegistryProvider(), existingFileHelper));
        generator.addProvider(event.includeServer(), new ModEntityTypeTagProvider(packOutput, builtin.getRegistryProvider(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ModLanguageProvider(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(packOutput, "ko_kr"));
    }
}
