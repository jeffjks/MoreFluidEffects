package dev.jeffjks.morefluideffects.api;

public interface FluidTypeExt {
    boolean mfx$isSuperHeated();
    void mfx$setSuperHeated(boolean v);

    boolean mfx$isVaporizesInUltraWarm();
    void mfx$setVaporizesInUltraWarm(boolean v);

    void mfx$locked();
}