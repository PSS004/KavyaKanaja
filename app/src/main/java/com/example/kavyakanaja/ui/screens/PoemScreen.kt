package com.example.kavyakanaja.ui.screens

import android.speech.tts.TextToSpeech
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kavyakanaja.data.model.Poem
import com.example.kavyakanaja.ui.theme.*
import com.example.kavyakanaja.viewmodel.PoemViewModel
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PoemScreen(poem: Poem, viewModel: PoemViewModel, modifier: Modifier = Modifier) {
    var expandedWord by remember { mutableStateOf<String?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    val isSaved = viewModel.favoritePoems.any { it.id == poem.id }

    val context = LocalContext.current
    val tts = remember { mutableStateOf<TextToSpeech?>(null) }

    LaunchedEffect(Unit) {
        tts.value = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.value?.language = Locale.forLanguageTag("kn-IN")
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(DeepIndigo, SpaceBlack),
                        radius = 2000f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .border(1.dp, BorderWhite, RoundedCornerShape(32.dp)),
                color = GlassWhite
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.AutoAwesome, 
                            contentDescription = null, 
                            tint = ElectricCyan,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = poem.kannadaTitle,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = poem.kannadaPoet,
                            style = MaterialTheme.typography.bodyLarge,
                            color = ElectricCyan.copy(alpha = 0.8f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(40.dp))
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(listOf(ElectricCyan.copy(alpha = 0.5f), Color.Transparent)),
                        shape = RoundedCornerShape(40.dp)
                    ),
                color = Color.White.copy(alpha = 0.03f)
            ) {
                Column(modifier = Modifier.padding(32.dp)) {
                    poem.verse.lines().forEach { line ->
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            line.split(" ").forEach { word ->
                                val cleanWord = word.trim().replace(Regex("[^\\u0C80-\\u0CFF]"), "")
                                val hasMeaning = poem.meanings.containsKey(cleanWord)
                                Text(
                                    text = "$word ",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontSize = 22.sp,
                                        lineHeight = 40.sp,
                                        fontWeight = if (hasMeaning) FontWeight.Bold else FontWeight.Normal
                                    ),
                                    color = if (hasMeaning) ElectricCyan else Color.White,
                                    modifier = Modifier
                                        .clickable(enabled = hasMeaning) { expandedWord = cleanWord }
                                        .padding(horizontal = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Futuristic Bhavartha Section
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp))
                    .border(1.dp, BorderWhite, RoundedCornerShape(32.dp)),
                color = Color.White.copy(alpha = 0.05f)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "BHAVARTHA",
                        style = MaterialTheme.typography.labelLarge,
                        color = VividMagenta,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = poem.description,
                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 24.sp),
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                GlassActionCard(
                    label = if (isSaved) "Saved" else "Save",
                    icon = if (isSaved) Icons.Rounded.Favorite else Icons.Default.AutoAwesome,
                    modifier = Modifier.fillMaxWidth(0.6f),
                    onClick = {
                        viewModel.toggleFavorite(poem.id)
                    },
                    color = if (isSaved) VividMagenta else SoftViolet
                )
            }
            
            Spacer(modifier = Modifier.height(120.dp))
        }

        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
                .fillMaxWidth()
                .height(90.dp)
                .clip(RoundedCornerShape(45.dp))
                .border(1.dp, BorderWhite, RoundedCornerShape(45.dp)),
            color = Color.White.copy(alpha = 0.1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Brush.linearGradient(AccentGradient)),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
                        isPlaying = !isPlaying
                        if (isPlaying) {
                            tts.value?.speak(poem.verse, TextToSpeech.QUEUE_FLUSH, null, "audio")
                        } else {
                            tts.value?.stop()
                        }
                    }) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                Column(modifier = Modifier.padding(start = 20.dp)) {
                    Text("NOW PLAYING", style = MaterialTheme.typography.labelLarge, color = ElectricCyan)
                    Text(poem.kannadaTitle, style = MaterialTheme.typography.bodyMedium, color = Color.White)
                }
            }
        }
    }

    if (expandedWord != null) {
        AlertDialog(
            onDismissRequest = { expandedWord = null },
            confirmButton = { Button(onClick = { expandedWord = null }) { Text("Got it") } },
            title = { Text(expandedWord!!, color = ElectricCyan) },
            text = { Text(poem.meanings[expandedWord] ?: "", color = Color.White) },
            containerColor = DeepIndigo,
            shape = RoundedCornerShape(32.dp)
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            tts.value?.stop()
            tts.value?.shutdown()
        }
    }
}

@Composable
fun GlassActionCard(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier, color: Color, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, BorderWhite, RoundedCornerShape(24.dp)),
        color = Color.White.copy(alpha = 0.05f)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null, tint = color)
            Spacer(modifier = Modifier.width(12.dp))
            Text(label, style = MaterialTheme.typography.labelLarge, color = Color.White)
        }
    }
}
