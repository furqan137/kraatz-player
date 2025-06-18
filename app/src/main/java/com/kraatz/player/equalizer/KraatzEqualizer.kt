package com.kraatz.player.equalizer

import android.media.audiofx.Equalizer
import android.media.audiofx.BassBoost
import android.media.audiofx.Virtualizer
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class KraatzEqualizer(private val audioSessionId: Int) {
    
    private var equalizer: Equalizer? = null
    private var bassBoost: BassBoost? = null
    private var virtualizer: Virtualizer? = null
    
    private val _isEnabled = MutableStateFlow(false)
    val isEnabled: StateFlow<Boolean> = _isEnabled.asStateFlow()
    
    private val _currentPreset = MutableStateFlow(KraatzPreset.NORMAL)
    val currentPreset: StateFlow<KraatzPreset> = _currentPreset.asStateFlow()
    
    private val _bandLevels = MutableStateFlow<List<Short>>(emptyList())
    val bandLevels: StateFlow<List<Short>> = _bandLevels.asStateFlow()
    
    private val _bassBoostStrength = MutableStateFlow(0)
    val bassBoostStrength: StateFlow<Int> = _bassBoostStrength.asStateFlow()
    
    private val _virtualizerStrength = MutableStateFlow(0)
    val virtualizerStrength: StateFlow<Int> = _virtualizerStrength.asStateFlow()
    
    init {
        initializeEffects()
    }
    
    private fun initializeEffects() {
        try {
            equalizer = Equalizer(0, audioSessionId).apply {
                enabled = false
            }
            
            bassBoost = BassBoost(0, audioSessionId).apply {
                enabled = false
            }
            
            virtualizer = Virtualizer(0, audioSessionId).apply {
                enabled = false
            }
            
            // Initialize band levels
            equalizer?.let { eq ->
                val bands = (0 until eq.numberOfBands).map { 
                    eq.getBandLevel(it.toShort()) 
                }
                _bandLevels.value = bands
            }
            
        } catch (e: Exception) {
            Log.e("KraatzEqualizer", "Failed to initialize effects", e)
        }
    }
    
    fun setEnabled(enabled: Boolean) {
        try {
            equalizer?.enabled = enabled
            bassBoost?.enabled = enabled
            virtualizer?.enabled = enabled
            _isEnabled.value = enabled
        } catch (e: Exception) {
            Log.e("KraatzEqualizer", "Failed to set enabled state", e)
        }
    }
    
    fun setBandLevel(band: Short, level: Short) {
        try {
            equalizer?.setBandLevel(band, level)
            updateBandLevels()
        } catch (e: Exception) {
            Log.e("KraatzEqualizer", "Failed to set band level", e)
        }
    }
    
    fun setBassBoost(strength: Int) {
        try {
            bassBoost?.setStrength(strength.toShort())
            _bassBoostStrength.value = strength
        } catch (e: Exception) {
            Log.e("KraatzEqualizer", "Failed to set bass boost", e)
        }
    }
    
    fun setVirtualizer(strength: Int) {
        try {
            virtualizer?.setStrength(strength.toShort())
            _virtualizerStrength.value = strength
        } catch (e: Exception) {
            Log.e("KraatzEqualizer", "Failed to set virtualizer", e)
        }
    }
    
    fun applyPreset(preset: KraatzPreset) {
        try {
            _currentPreset.value = preset
            
            when (preset) {
                KraatzPreset.NORMAL -> applyNormalPreset()
                KraatzPreset.ROCK -> applyRockPreset()
                KraatzPreset.POP -> applyPopPreset()
                KraatzPreset.JAZZ -> applyJazzPreset()
                KraatzPreset.CLASSICAL -> applyClassicalPreset()
                KraatzPreset.ELECTRONIC -> applyElectronicPreset()
                KraatzPreset.KRAATZ_SIGNATURE -> applyKraatzSignaturePreset()
            }
            
            updateBandLevels()
        } catch (e: Exception) {
            Log.e("KraatzEqualizer", "Failed to apply preset", e)
        }
    }
    
    private fun applyNormalPreset() {
        equalizer?.let { eq ->
            for (i in 0 until eq.numberOfBands) {
                eq.setBandLevel(i.toShort(), 0)
            }
        }
        setBassBoost(0)
        setVirtualizer(0)
    }
    
    private fun applyRockPreset() {
        val levels = shortArrayOf(800, 400, -200, -800, -400, 400, 800, 1100, 1200, 1200)
        applyBandLevels(levels)
        setBassBoost(300)
        setVirtualizer(500)
    }
    
    private fun applyPopPreset() {
        val levels = shortArrayOf(-200, 400, 700, 800, 500, 0, -200, -200, -200, -200)
        applyBandLevels(levels)
        setBassBoost(200)
        setVirtualizer(300)
    }
    
    private fun applyJazzPreset() {
        val levels = shortArrayOf(400, 200, 0, 200, -200, -200, 0, 200, 400, 500)
        applyBandLevels(levels)
        setBassBoost(100)
        setVirtualizer(400)
    }
    
    private fun applyClassicalPreset() {
        val levels = shortArrayOf(500, 300, -200, -200, -200, 0, 200, 300, 400, 500)
        applyBandLevels(levels)
        setBassBoost(0)
        setVirtualizer(600)
    }
    
    private fun applyElectronicPreset() {
        val levels = shortArrayOf(500, 400, 0, -200, -500, 200, 800, 900, 900, 800)
        applyBandLevels(levels)
        setBassBoost(400)
        setVirtualizer(700)
    }
    
    private fun applyKraatzSignaturePreset() {
        // Unique Kraatz signature sound - enhanced clarity with warm bass
        val levels = shortArrayOf(600, 400, 200, 0, -100, 100, 300, 500, 700, 800)
        applyBandLevels(levels)
        setBassBoost(350)
        setVirtualizer(550)
    }
    
    private fun applyBandLevels(levels: ShortArray) {
        equalizer?.let { eq ->
            val bandCount = kotlin.math.min(levels.size, eq.numberOfBands.toInt())
            for (i in 0 until bandCount) {
                eq.setBandLevel(i.toShort(), levels[i])
            }
        }
    }
    
    private fun updateBandLevels() {
        equalizer?.let { eq ->
            val bands = (0 until eq.numberOfBands).map { 
                eq.getBandLevel(it.toShort()) 
            }
            _bandLevels.value = bands
        }
    }
    
    fun getBandFrequency(band: Short): Int {
        return try {
            equalizer?.getCenterFreq(band) ?: 0
        } catch (e: Exception) {
            0
        }
    }
    
    fun getBandFrequencyRange(band: Short): IntArray {
        return try {
            equalizer?.getBandFreqRange(band) ?: intArrayOf(0, 0)
        } catch (e: Exception) {
            intArrayOf(0, 0)
        }
    }
    
    fun getBandLevelRange(): ShortArray {
        return try {
            equalizer?.bandLevelRange ?: shortArrayOf(0, 0)
        } catch (e: Exception) {
            shortArrayOf(0, 0)
        }
    }
    
    fun getNumberOfBands(): Short {
        return try {
            equalizer?.numberOfBands?.toShort() ?: 0
        } catch (e: Exception) {
            0
        }
    }
    
    fun release() {
        try {
            equalizer?.release()
            bassBoost?.release()
            virtualizer?.release()
        } catch (e: Exception) {
            Log.e("KraatzEqualizer", "Failed to release effects", e)
        }
    }
}

enum class KraatzPreset {
    NORMAL,
    ROCK,
    POP,
    JAZZ,
    CLASSICAL,
    ELECTRONIC,
    KRAATZ_SIGNATURE
}
