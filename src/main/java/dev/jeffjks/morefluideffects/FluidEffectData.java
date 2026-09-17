package dev.jeffjks.morefluideffects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.jeffjks.morefluideffects.fluidbehaviour.FluidEffect;

import java.util.List;

public record FluidEffectData(
        boolean canExtinguish,
        boolean vaporizesInUltraWarm,
        List<FluidEffect> effects
) {
    public static final Codec<FluidEffectData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("canExtinguish").forGetter(FluidEffectData::canExtinguish),
            Codec.BOOL.fieldOf("vaporizesInUltraWarm").forGetter(FluidEffectData::vaporizesInUltraWarm),
            FluidEffectFactories.CODEC.listOf().optionalFieldOf("effects", List.of()).forGetter(FluidEffectData::effects)
    ).apply(instance, FluidEffectData::new));

    public FluidEffectData(boolean canExtinguish, boolean vaporizesInUltraWarm) {
        this(canExtinguish, vaporizesInUltraWarm, List.of());
    }
}