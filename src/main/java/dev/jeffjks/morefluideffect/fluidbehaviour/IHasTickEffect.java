package dev.jeffjks.morefluideffect.fluidbehaviour;

import net.minecraft.world.entity.LivingEntity;

public interface IHasTickEffect {
    void tick(LivingEntity living, int tickCount);
}
