package com.kraatz.player.equalizer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\f\n\u0002\u0010\u0015\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020!H\u0002J\b\u0010%\u001a\u00020!H\u0002J\b\u0010&\u001a\u00020!H\u0002J\b\u0010\'\u001a\u00020!H\u0002J\b\u0010(\u001a\u00020!H\u0002J\b\u0010)\u001a\u00020!H\u0002J\u000e\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020\u000bJ\b\u0010,\u001a\u00020!H\u0002J\u000e\u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\bJ\u000e\u0010/\u001a\u0002002\u0006\u0010.\u001a\u00020\bJ\u0006\u00101\u001a\u00020#J\u0006\u00102\u001a\u00020\bJ\b\u00103\u001a\u00020!H\u0002J\u0006\u00104\u001a\u00020!J\u0016\u00105\u001a\u00020!2\u0006\u0010.\u001a\u00020\b2\u0006\u00106\u001a\u00020\bJ\u000e\u00107\u001a\u00020!2\u0006\u00108\u001a\u00020\u0003J\u000e\u00109\u001a\u00020!2\u0006\u0010:\u001a\u00020\rJ\u000e\u0010;\u001a\u00020!2\u0006\u00108\u001a\u00020\u0003J\b\u0010<\u001a\u00020!H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0012\u00a8\u0006="}, d2 = {"Lcom/kraatz/player/equalizer/KraatzEqualizer;", "", "audioSessionId", "", "(I)V", "_bandLevels", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "_bassBoostStrength", "_currentPreset", "Lcom/kraatz/player/equalizer/KraatzPreset;", "_isEnabled", "", "_virtualizerStrength", "bandLevels", "Lkotlinx/coroutines/flow/StateFlow;", "getBandLevels", "()Lkotlinx/coroutines/flow/StateFlow;", "bassBoost", "Landroid/media/audiofx/BassBoost;", "bassBoostStrength", "getBassBoostStrength", "currentPreset", "getCurrentPreset", "equalizer", "Landroid/media/audiofx/Equalizer;", "isEnabled", "virtualizer", "Landroid/media/audiofx/Virtualizer;", "virtualizerStrength", "getVirtualizerStrength", "applyBandLevels", "", "levels", "", "applyClassicalPreset", "applyElectronicPreset", "applyJazzPreset", "applyKraatzSignaturePreset", "applyNormalPreset", "applyPopPreset", "applyPreset", "preset", "applyRockPreset", "getBandFrequency", "band", "getBandFrequencyRange", "", "getBandLevelRange", "getNumberOfBands", "initializeEffects", "release", "setBandLevel", "level", "setBassBoost", "strength", "setEnabled", "enabled", "setVirtualizer", "updateBandLevels", "app_debug"})
public final class KraatzEqualizer {
    private final int audioSessionId = 0;
    private android.media.audiofx.Equalizer equalizer;
    private android.media.audiofx.BassBoost bassBoost;
    private android.media.audiofx.Virtualizer virtualizer;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isEnabled = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isEnabled = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.kraatz.player.equalizer.KraatzPreset> _currentPreset = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.kraatz.player.equalizer.KraatzPreset> currentPreset = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.Short>> _bandLevels = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.Short>> bandLevels = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _bassBoostStrength = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> bassBoostStrength = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _virtualizerStrength = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> virtualizerStrength = null;
    
    public KraatzEqualizer(int audioSessionId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isEnabled() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.kraatz.player.equalizer.KraatzPreset> getCurrentPreset() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.Short>> getBandLevels() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getBassBoostStrength() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getVirtualizerStrength() {
        return null;
    }
    
    private final void initializeEffects() {
    }
    
    public final void setEnabled(boolean enabled) {
    }
    
    public final void setBandLevel(short band, short level) {
    }
    
    public final void setBassBoost(int strength) {
    }
    
    public final void setVirtualizer(int strength) {
    }
    
    public final void applyPreset(@org.jetbrains.annotations.NotNull
    com.kraatz.player.equalizer.KraatzPreset preset) {
    }
    
    private final void applyNormalPreset() {
    }
    
    private final void applyRockPreset() {
    }
    
    private final void applyPopPreset() {
    }
    
    private final void applyJazzPreset() {
    }
    
    private final void applyClassicalPreset() {
    }
    
    private final void applyElectronicPreset() {
    }
    
    private final void applyKraatzSignaturePreset() {
    }
    
    private final void applyBandLevels(short[] levels) {
    }
    
    private final void updateBandLevels() {
    }
    
    public final int getBandFrequency(short band) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final int[] getBandFrequencyRange(short band) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final short[] getBandLevelRange() {
        return null;
    }
    
    public final short getNumberOfBands() {
        return 0;
    }
    
    public final void release() {
    }
}