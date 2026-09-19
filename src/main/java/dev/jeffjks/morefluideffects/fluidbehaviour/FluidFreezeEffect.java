package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidFreezeEffect extends FluidEffect {
    public static final String TYPE = MoreFluidEffects.MOD_ID + ":freezing";

    public static final MapCodec<FluidFreezeEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("frozenTicks").forGetter(e -> e.frozenTicks),
            Codec.INT.fieldOf("maxFrozenTicks").forGetter(e -> e.maxFrozenTicks)
    ).apply(instance, FluidFreezeEffect::new));

    private final int frozenTicks;
    private final int maxFrozenTicks;

    public FluidFreezeEffect(int frozenTicks, int maxFrozenTicks) {
        super(TYPE, 1);
        this.frozenTicks = frozenTicks;
        this.maxFrozenTicks = maxFrozenTicks;
    }

    @Override
    protected void apply(Entity entity) {
        if (entity instanceof LivingEntity living) {
            living.setTicksFrozen(Math.min(living.getTicksFrozen() + frozenTicks + 2, maxFrozenTicks)); // -2 every tick in vanilla
        }
    }
}
