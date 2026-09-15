package dev.jeffjks.morefluideffects;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_FLUID_EFFECTS = BUILDER
            .comment("Master switch for this mod's fluid effects. If set to false, the mod's fluid effect " +
                    "definitions in the config file will not be applied, and no fluids will be affected by " +
                    "this mod at all, regardless of what the config file contains.")
            .define("enableFluidEffects", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
