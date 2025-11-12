package dev.jeffjks.morefluideffects;

import dev.jeffjks.morefluideffects.fluidbehaviour.DefaultFluidGroups;
import dev.jeffjks.morefluideffects.fluidbehaviour.FluidEffect;

import java.io.File;
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
            boolean canExtinguish = fm.canExtinguish;
            boolean isVaporize = fm.vaporizesInUltraWarm;
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
}
