package dev.jeffjks.morefluideffect.fluidbehaviour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidExtinguishFireEffect extends FluidEffect {

    public FluidExtinguishFireEffect() {
        super(1);
    }

    @Override
    protected void apply(LivingEntity living) {
        living.extinguishFire();
    }
}
