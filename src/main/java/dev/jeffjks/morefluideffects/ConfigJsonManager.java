package dev.jeffjks.morefluideffects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.jeffjks.morefluideffects.fluidbehaviour.DefaultFluidGroups;
import dev.jeffjks.morefluideffects.fluidbehaviour.FluidEffectsRegistry;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigJsonManager<T> {

    private final File file;
    private final Class<T> configClass;
    private final Gson gson;

    public ConfigJsonManager(File file, Class<T> configClass) {
        this.file = file;
        this.configClass = configClass;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
    }

    public T loadOrCreate(T defaultConfig) {
        if (!file.exists()) {
            save(defaultConfig);
            return defaultConfig;
        }

        try (FileReader reader = new FileReader(file)) {
            FluidEffectsRegistry.reset();
            return gson.fromJson(reader, configClass);
        } catch (Exception e) {
            MoreFluidEffects.LOGGER.error(e.getMessage());
            save(defaultConfig);
            return defaultConfig;
        }
    }

    public void save(T config) {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(config, writer);
        } catch (IOException e) {
            MoreFluidEffects.LOGGER.error(e.getMessage());
        }
    }
}