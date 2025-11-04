package dev.jeffjks.morefluideffect.datagen;

import dev.jeffjks.morefluideffect.MoreFluidEffect;
import dev.jeffjks.morefluideffect.common.registry.DamageTypeRegister;
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

@EventBusSubscriber(modid = MoreFluidEffect.MODID)
public class DataGenerators {

    private DataGenerators() {
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        //var datapackProvider = generator.addProvider(event.includeServer(), new ModDatapackProvider(packOutput, lookupProvider));

        //generator.addProvider(event.includeServer(), new ModDamageTypeTagProvider(packOutput, datapackProvider.getRegistryProvider(), existingFileHelper));


        var builder = new RegistrySetBuilder()
                .add(Registries.DAMAGE_TYPE, DamageTypeRegister::bootstrap);
        var builtin = new DatapackBuiltinEntriesProvider(
                packOutput, lookupProvider, builder, Set.of(MoreFluidEffect.MODID)
        );
        generator.addProvider(event.includeServer(), builtin);

        //generator.addProvider(event.includeServer(), new ModDamageTypeTagProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModDamageTypeTagProvider(packOutput, builtin.getRegistryProvider(), existingFileHelper));
    }
}
