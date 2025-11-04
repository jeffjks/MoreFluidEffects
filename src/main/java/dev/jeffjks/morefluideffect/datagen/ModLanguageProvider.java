package dev.jeffjks.morefluideffect.datagen;

import dev.jeffjks.morefluideffect.MoreFluidEffect;
import dev.jeffjks.morefluideffect.common.registry.ModDamageTypes;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    final private String locale;

    public ModLanguageProvider(PackOutput output, String locale) {
        this(output, MoreFluidEffect.MODID, locale);
    }

    public ModLanguageProvider(PackOutput output) {
        this(output, MoreFluidEffect.MODID, "en_us");
    }

    public ModLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
        this.locale = locale;
    }

    @Override
    protected void addTranslations() {
        switch (this.locale) {
            case "ko_kr":
                this.addKoreaTranslations();
                break;
            case "en_us":
            default:
                this.addEnglishTranslations();
        }
    }

    // ko_kr
    protected void addKoreaTranslations() {
        add(ModDamageTypes.ACID, "%1$s이(가) 산성 물질에 빠졌습니다.", "%1$s이(가) %2$s로부터 도망치려다가 산성 물질에 빠졌습니다.");
        add(ModDamageTypes.HEAT, "%1$s이(가) 고온 물질에 빠졌습니다.", "%1$s이(가) %2$s로부터 도망치려다가 고온 물질에 빠졌습니다.");
    }

    // en_us
    protected void addEnglishTranslations() {
        add(ModDamageTypes.ACID, "%1$s tried to swim in acid.", "%1$s tried to swim in acid to escape %2$s.");
        add(ModDamageTypes.HEAT, "%1$s tried to swim in high temperature material.", "%1$s tried to swim in high temperature material to escape %2$s.");
    }

    protected void add(ResourceKey<DamageType> damageType, String translate, String byPlayerTranslate) {
        add("death.attack." + damageType.location().toLanguageKey(), translate);
        add("death.attack." + damageType.location().toLanguageKey() + ".player", byPlayerTranslate);
    }

    protected void add(String pre, String post, String translate) {
        add(pre + ":" + post, translate);
    }

    protected void add(String pre, ResourceLocation post, String translate) {
        add(post.toLanguageKey(pre), translate);
    }
}
