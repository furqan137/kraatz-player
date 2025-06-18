package com.kraatz.player.viewmodel;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\nJ\u000e\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0006J\u000e\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u0018J\u0016\u0010\"\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006J\u000e\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\bJ\u000e\u0010&\u001a\u00020\u001c2\u0006\u0010\'\u001a\u00020\fJ\u000e\u0010(\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\bJ\b\u0010)\u001a\u00020\u001cH\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011\u00a8\u0006*"}, d2 = {"Lcom/kraatz/player/viewmodel/EqualizerViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_bandLevels", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "_bassBoostStrength", "", "_currentPreset", "Lcom/kraatz/player/equalizer/KraatzPreset;", "_isEnabled", "", "_virtualizerStrength", "bandLevels", "Lkotlinx/coroutines/flow/StateFlow;", "getBandLevels", "()Lkotlinx/coroutines/flow/StateFlow;", "bassBoostStrength", "getBassBoostStrength", "currentPreset", "getCurrentPreset", "isEnabled", "kraatzEqualizer", "Lcom/kraatz/player/equalizer/KraatzEqualizer;", "virtualizerStrength", "getVirtualizerStrength", "applyPreset", "", "preset", "getBandFrequency", "band", "initializeEqualizer", "equalizer", "setBandLevel", "level", "setBassBoost", "strength", "setEnabled", "enabled", "setVirtualizer", "updateBandLevels", "app_debug"})
public final class EqualizerViewModel extends androidx.lifecycle.ViewModel {
    private com.kraatz.player.equalizer.KraatzEqualizer kraatzEqualizer;
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
    
    @javax.inject.Inject
    public EqualizerViewModel() {
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
    
    public final void setEnabled(boolean enabled) {
    }
    
    public final void applyPreset(@org.jetbrains.annotations.NotNull
    com.kraatz.player.equalizer.KraatzPreset preset) {
    }
    
    public final void setBandLevel(short band, short level) {
    }
    
    public final void setBassBoost(int strength) {
    }
    
    public final void setVirtualizer(int strength) {
    }
    
    public final int getBandFrequency(short band) {
        return 0;
    }
    
    private final void updateBandLevels() {
    }
    
    public final void initializeEqualizer(@org.jetbrains.annotations.NotNull
    com.kraatz.player.equalizer.KraatzEqualizer equalizer) {
    }
}