package dev.jeffjks.morefluideffect.fluidbehaviour;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import java.util.Optional;

public class FluidExplosionOnFireEffect extends FluidEffect {

    public FluidExplosionOnFireEffect() {
        super(1);
    }

    @Override
    protected void apply(LivingEntity living) {
        if (living.wasOnFire) {
            Level level = living.level();

            level.explode(null, Explosion.getDefaultDamageSource(level, living), null,
                    living.getX(), living.getY(0.0625F), living.getZ(), 4.0F, true, Level.ExplosionInteraction.TNT);
            living.extinguishFire();
        }
    }
}
