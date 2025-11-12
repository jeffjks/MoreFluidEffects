package dev.jeffjks.morefluideffects;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class FluidEffectJsonData {
    public List<FluidMapping> fluidMappings = new ArrayList<>();

    public static class FluidMapping {
        public String id;
        public boolean canExtinguish = true;
        public boolean vaporizesInUltraWarm = true;
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