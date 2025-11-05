package dev.jeffjks.morefluideffects.utils;

import dev.jeffjks.morefluideffects.MoreFluidEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public class TagRegister {


    public static class DamageTypes {

        private DamageTypes() {
        }

        public static final TagKey<DamageType> ACID = tag("acid");

        private static TagKey<DamageType> tag(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(MoreFluidEffects.MODID, name));
        }
    }
}
