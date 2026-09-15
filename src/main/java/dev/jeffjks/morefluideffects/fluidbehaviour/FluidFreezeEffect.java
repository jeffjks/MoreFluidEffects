package dev.jeffjks.morefluideffects.fluidbehaviour;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FluidFreezeEffect extends FluidEffect {

    private final int frozenTicks;
    private final int maxFrozenTicks;

    public FluidFreezeEffect(int frozenTicks, int maxFrozenTicks) {
        super(1);
        this.frozenTicks = frozenTicks;
        this.maxFrozenTicks = maxFrozenTicks;
    }

    @Override
    protected void apply(Entity entity) {
        if (entity instanceof LivingEntity living) {
            living.setTicksFrozen(Math.min(living.getTicksFrozen() + frozenTicks + 2, maxFrozenTicks)); // -2 every tick in vanilla
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("frozenTicks", frozenTicks);
        obj.addProperty("maxFrozenTicks", maxFrozenTicks);
        obj.addProperty("interval", interval);
        return obj;
    }
}
