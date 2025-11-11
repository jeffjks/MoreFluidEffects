package dev.jeffjks.morefluideffects;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FluidEffectJsonData {
    public List<FluidMapping> fluidMappings = new ArrayList<>();

    public static class FluidMapping {
        public String id;
        public boolean can_extinguish = true;
        public boolean vaporizes_in_ultra_warm = true;
        public List<FluidEffectType> effects = new ArrayList<>();
    }

    public static class FluidEffectType {

        public FluidEffectType(String type) {
            this.type = type;
        }

        public String type;
        public JsonObject params;
    }

    public void addFluidMapping(FluidMapping mapping) {
        fluidMappings.add(mapping);
    }
}