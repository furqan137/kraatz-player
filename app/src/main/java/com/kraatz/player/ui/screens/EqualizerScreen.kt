package com.kraatz.player.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class EqualizerBand(
    val frequency: String,
    val gain: Float
)

data class EqualizerPreset(
    val name: String,
    val bands: List<Float>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EqualizerScreen(
    isEnabled: Boolean = false,
    currentPreset: com.kraatz.player.equalizer.KraatzPreset = com.kraatz.player.equalizer.KraatzPreset.NORMAL,
    bandLevels: List<Short> = emptyList(),
    bassBoostStrength: Int = 0,
    virtualizerStrength: Int = 0,
    onEnabledChange: (Boolean) -> Unit = {},
    onPresetChange: (com.kraatz.player.equalizer.KraatzPreset) -> Unit = {},
    onBandLevelChange: (Int, Short) -> Unit = { _, _ -> },
    onBassBoostChange: (Int) -> Unit = {},
    onVirtualizerChange: (Int) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    // Equalizer state
    var selectedPreset by remember { mutableStateOf("Custom") }
    var bands by remember { 
        mutableStateOf(
            listOf(
                EqualizerBand("60Hz", 0f),
                EqualizerBand("170Hz", 0f),
                EqualizerBand("310Hz", 0f),
                EqualizerBand("600Hz", 0f),
                EqualizerBand("1kHz", 0f),
                EqualizerBand("3kHz", 0f),
                EqualizerBand("6kHz", 0f),
                EqualizerBand("12kHz", 0f),
                EqualizerBand("14kHz", 0f),
                EqualizerBand("16kHz", 0f)
            )
        )
    }
    
    // Equalizer presets
    val presets = listOf(
        EqualizerPreset("Flat", listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)),
        EqualizerPreset("Rock", listOf(5f, 3f, -1f, -2f, 1f, 2f, 4f, 6f, 7f, 7f)),
        EqualizerPreset("Pop", listOf(-1f, 2f, 4f, 4f, 2f, -1f, -2f, -2f, -1f, -1f)),
        EqualizerPreset("Jazz", listOf(4f, 3f, 1f, 2f, -1f, -1f, 0f, 1f, 2f, 3f)),
        EqualizerPreset("Classical", listOf(5f, 4f, 3f, 2f, -1f, -1f, 0f, 2f, 3f, 4f)),
        EqualizerPreset("Bass Boost", listOf(7f, 6f, 5f, 3f, 1f, -1f, -2f, -3f, -3f, -3f)),
        EqualizerPreset("Treble Boost", listOf(-3f, -3f, -2f, -1f, 1f, 3f, 5f, 6f, 7f, 8f)),
        EqualizerPreset("Vocal", listOf(-2f, -1f, 2f, 4f, 4f, 4f, 3f, 2f, 1f, 0f))
    )
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text("Kraatz Equalizer") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = { /* TODO: Settings */ }) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings")
                }
            }
        )
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🎛️ 10-Band Graphic Equalizer",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Fine-tune your audio experience",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
            
            // Preset Selection
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Presets",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        // Preset buttons
                        presets.chunked(2).forEach { presetRow ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                presetRow.forEach { preset ->
                                    Button(
                                        onClick = {
                                            selectedPreset = preset.name
                                            bands = bands.mapIndexed { index, band ->
                                                band.copy(gain = preset.bands[index])
                                            }
                                        },
                                        modifier = Modifier.weight(1f),
                                        colors = if (selectedPreset == preset.name) {
                                            ButtonDefaults.buttonColors()
                                        } else {
                                            ButtonDefaults.outlinedButtonColors()
                                        }
                                    ) {
                                        Text(preset.name)
                                    }
                                }
                                // Fill remaining space if odd number of presets in row
                                if (presetRow.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
            
            // Equalizer Bands
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Frequency Bands",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Equalizer sliders
                        bands.forEachIndexed { index, band ->
                            EqualizerBandSlider(
                                band = band,
                                onValueChange = { newGain ->
                                    bands = bands.toMutableList().apply {
                                        this[index] = band.copy(gain = newGain)
                                    }
                                    selectedPreset = "Custom"
                                }
                            )
                            if (index < bands.size - 1) {
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                }
            }
            
            // Controls
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Controls",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = {
                                    bands = bands.map { it.copy(gain = 0f) }
                                    selectedPreset = "Flat"
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Reset")
                            }
                            
                            Button(
                                onClick = {
                                    // TODO: Apply equalizer settings
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Apply")
                            }
                        }
                    }
                }
            }
            
            // Info
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "ℹ️ About Kraatz Equalizer",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "• 10-band graphic equalizer with ±15dB range\n" +
                                  "• 8 built-in presets for different music genres\n" +
                                  "• Real-time audio processing\n" +
                                  "• Custom preset creation and saving",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EqualizerBandSlider(
    band: EqualizerBand,
    onValueChange: (Float) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = band.frequency,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${if (band.gain >= 0) "+" else ""}${String.format("%.1f", band.gain)}dB",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        
        Slider(
            value = band.gain,
            onValueChange = onValueChange,
            valueRange = -15f..15f,
            steps = 29, // 30 steps total for 1dB increments
            modifier = Modifier.fillMaxWidth()
        )
    }
}
