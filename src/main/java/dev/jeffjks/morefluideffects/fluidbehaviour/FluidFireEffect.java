package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;

import java.util.Locale;

public class FluidFireEffect extends FluidEffect {
    public static final String TYPE = MoreFluidEffects.MOD_ID + ":fire";

    public static final MapCodec<FluidFireEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            StringRepresentable.fromEnum(FireMode::values).fieldOf("mode").forGetter(e -> e.mode),
            Codec.INT.fieldOf("ticks").forGetter(e -> e.ticks)
    ).apply(instance, FluidFireEffect::new));

    public enum FireMode implements StringRepresentable {
        EXTEND,
        IGNITE;

        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

    private final FireMode mode;
    private final int ticks; // EXTINGUISH: Not Used / EXTEND: fireTicks / IGNITE: igniteTicks

    public FluidFireEffect(FireMode mode, int ticks) {
        super(TYPE, 1);
        this.mode = mode;
        this.ticks = ticks;
    }

    public static FluidFireEffect extend(int fireTicks) {
        return new FluidFireEffect(FireMode.EXTEND, fireTicks);
    }

    public static FluidFireEffect ignite(int igniteTicks) {
        return new FluidFireEffect(FireMode.IGNITE, igniteTicks);
    }

    public FireMode getMode() {
        return mode;
    }

    @Override
    protected void apply(Entity entity) {
        switch (mode) {
            case EXTEND -> {
                if (entity.getRemainingFireTicks() > 0)
                    entity.setRemainingFireTicks(ticks);
            }
            case IGNITE -> entity.igniteForSeconds((float) ticks / 20f);
        }
    }
}