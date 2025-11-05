package dev.jeffjks.morefluideffects.fluidbehaviour;

import net.minecraft.world.entity.LivingEntity;

public interface IHasTickEffect {
    void tick(LivingEntity living);
}
