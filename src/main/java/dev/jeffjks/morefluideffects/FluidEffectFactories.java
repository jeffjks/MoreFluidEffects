package dev.jeffjks.morefluideffects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import dev.jeffjks.morefluideffects.fluidbehaviour.*;

import java.util.HashMap;
import java.util.Map;

public final class FluidEffectFactories {

    private FluidEffectFactories() {}

    private static final Map<String, MapCodec<? extends FluidEffect>> REGISTRY = new HashMap<>();

    static {
        register(FluidDamageEffect.TYPE, FluidDamageEffect.CODEC);
        register(FluidFreezeEffect.TYPE, FluidFreezeEffect.CODEC);
        register(FluidFireEffect.TYPE, FluidFireEffect.CODEC);
        register(FluidStatusEffect.TYPE, FluidStatusEffect.CODEC);
        register(FluidExplosionOnFireEffect.TYPE, FluidExplosionOnFireEffect.CODEC);
        register(FluidWaterLikeEffect.TYPE, FluidWaterLikeEffect.CODEC);
    }

    private static <T extends FluidEffect> void register(String type, MapCodec<T> codec) {
        REGISTRY.put(type, codec);
    }

    private static DataResult<? extends MapCodec<? extends FluidEffect>> codecFor(String type) {
        MapCodec<? extends FluidEffect> codec = REGISTRY.get(type);
        return codec != null
                ? DataResult.success(codec)
                : DataResult.error(() -> "Unknown fluid effect type: " + type);
    }

    public static final Codec<FluidEffect> CODEC =
            Codec.STRING.partialDispatch(
                    "type",
                    fx -> DataResult.success(fx.getType()),
                    FluidEffectFactories::codecFor
            );
}