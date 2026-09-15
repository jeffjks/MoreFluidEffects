package dev.jeffjks.morefluideffects;

import com.google.gson.*;
import java.lang.reflect.Type;

public class FluidEffectTypeAdapter implements JsonSerializer<FluidEffectJsonData.FluidEffectType>,
        JsonDeserializer<FluidEffectJsonData.FluidEffectType> {

    @Override
    public JsonElement serialize(FluidEffectJsonData.FluidEffectType src, Type typeOfSrc, JsonSerializationContext ctx) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", src.type);
        if (src.params != null) {
            for (var entry : src.params.entrySet()) {
                obj.add(entry.getKey(), entry.getValue());
            }
        }
        return obj;
    }

    @Override
    public FluidEffectJsonData.FluidEffectType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) {
        JsonObject obj = json.getAsJsonObject();
        String type = obj.get("type").getAsString();

        var result = new FluidEffectJsonData.FluidEffectType(type);
        JsonObject params = obj.deepCopy();
        params.remove("type");
        result.params = params;
        return result;
    }
}