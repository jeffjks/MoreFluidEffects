package dev.jeffjks.morefluideffects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.jeffjks.morefluideffects.fluidbehaviour.DefaultFluidGroups;
import dev.jeffjks.morefluideffects.fluidbehaviour.FluidEffect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConfigJsonApplier {

    public static final String CONFIG_DIR = "config";

    public static void loadConfig() {
        File jsonFile = new File(CONFIG_DIR, MoreFluidEffects.MOD_ID + ".json");

        ConfigJsonManager<FluidEffectJsonData> configManager =
                new ConfigJsonManager<>(jsonFile, FluidEffectJsonData.class);

        var defaultJsonData = DefaultFluidGroups.registerDefaultFluidEffects();
        FluidEffectJsonData jsonData = configManager.loadOrCreate(defaultJsonData);

        applyConfig(jsonData);
    }

    private static void applyConfig(FluidEffectJsonData cfg) {
        for (FluidEffectJsonData.FluidMapping fm : cfg.fluidMappings) {
            boolean canExtinguish = fm.can_extinguish;
            boolean isVaporize = fm.vaporizes_in_ultra_warm;
            List<FluidEffect> effects = new ArrayList<>();

            for (FluidEffectJsonData.FluidEffectType spec : fm.effects) {
                if (spec.type == null) {
                    MoreFluidEffects.LOGGER.error("Fluid Effect type value is null!");
                    continue;
                }
                FluidEffect effect = FluidEffectFactories.create(spec.type, spec.params);
                effects.add(effect);
            }

            DefaultFluidGroups.mapFluidEffect(fm.id, canExtinguish, isVaporize, effects);
        }
    }

    private static void setDefaultJson(File jsonFile, FluidEffectJsonData jsonData) {
        try {
            jsonFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        try (Writer writer = new FileWriter(jsonFile, StandardCharsets.UTF_8)) {
            gson.toJson(jsonData, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
