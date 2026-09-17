package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.jeffjks.morefluideffects.MoreFluidEffects;
import dev.jeffjks.morefluideffects.utils.MobEffectsHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class FluidStatusEffect extends FluidEffect {
    public static final String TYPE = MoreFluidEffects.MOD_ID + ":status_effect";

    public static final MapCodec<FluidStatusEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceKey.codec(Registries.MOB_EFFECT).fieldOf("mobEffectId").forGetter(e -> e.mobEffectId),
            Codec.INT.fieldOf("duration").forGetter(e -> e.duration),
            Codec.INT.fieldOf("effectLevel").forGetter(e -> e.effectLevel),
            Codec.INT.fieldOf("interval").forGetter(e -> e.interval)
    ).apply(instance, FluidStatusEffect::new));

    private final ResourceKey<MobEffect> mobEffectId;
    private final int duration;
    private final int effectLevel;

    public FluidStatusEffect(ResourceKey<MobEffect> mobEffectId, int duration, int effectLevel, int interval) {
        super(TYPE, interval);
        this.mobEffectId = mobEffectId;
        this.duration = duration;
        this.effectLevel = effectLevel;
    }

    @Override
    protected void apply(Entity entity) {
        if (!(entity instanceof LivingEntity living))
            return;

        Holder<MobEffect> holder = entity.level().registryAccess()
                .registryOrThrow(Registries.MOB_EFFECT)
                .getHolderOrThrow(mobEffectId);

        living.addEffect(new MobEffectInstance(holder, duration, effectLevel - 1));
    }

    @Override
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("mobEffectId", mobEffectId.location().toString());
        obj.addProperty("duration", duration);
        obj.addProperty("effectLevel", effectLevel);
        obj.addProperty("interval", interval);
        return obj;
    }
}
