package dev.jeffjks.morefluideffect.common.event;

import dev.jeffjks.morefluideffect.common.registry.ModDamageTypes;
import dev.jeffjks.morefluideffect.common.registry.ModEntityTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;

public class EventHandler {
    @SubscribeEvent
    public void checkEntityInvulnerability(EntityInvulnerabilityCheckEvent event) {
        if (event.isInvulnerable())
            return;
        if (event.getEntity() instanceof LivingEntity living) {
            if (event.getSource().is(ModDamageTypes.ACID)) {
                event.setInvulnerable(living.getType().is(ModEntityTypeTags.IMMUNE_TO_ACID));
            }
            if (event.getSource().is(DamageTypes.FREEZE)) {
                event.setInvulnerable(living.getType().is(ModEntityTypeTags.IMMUNE_TO_CRYOGENIC));
                living.setTicksFrozen(0);
            }
        }
    }
}
