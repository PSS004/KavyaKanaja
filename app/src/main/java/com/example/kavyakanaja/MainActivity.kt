package com.example.kavyakanaja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kavyakanaja.viewmodel.PoemViewModel
import com.example.kavyakanaja.viewmodel.PoetViewModel
import com.example.kavyakanaja.ui.screens.PoemScreen
import com.example.kavyakanaja.ui.screens.PoetScreen
import com.example.kavyakanaja.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KavyaKanajaTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedScreen by remember { mutableStateOf("poem") }
    val poemViewModel: PoemViewModel = viewModel()

    Scaffold(
        bottomBar = {
            Surface(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .border(1.dp, BorderWhite, RoundedCornerShape(40.dp)),
                color = Color.White.copy(alpha = 0.05f)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    FuturisticNavItem(
                        selected = selectedScreen == "poem",
                        onClick = { selectedScreen = "poem" },
                        icon = Icons.Default.RocketLaunch
                    )
                    FuturisticNavItem(
                        selected = selectedScreen == "poet",
                        onClick = { selectedScreen = "poet" },
                        icon = Icons.Default.HistoryEdu
                    )
                    FuturisticNavItem(
                        selected = selectedScreen == "saved",
                        onClick = { selectedScreen = "saved" },
                        icon = Icons.Default.AutoAwesome
                    )
                }
            }
        },
        containerColor = SpaceBlack
    ) { padding ->
        Box(modifier = Modifier.padding(bottom = padding.calculateBottomPadding())) {
            AnimatedContent(
                targetState = selectedScreen,
                transitionSpec = {
                    slideInVertically { it } + fadeIn() togetherWith slideOutVertically { -it } + fadeOut()
                },
                label = "ScreenTransition"
            ) { screen ->
                when (screen) {
                    "poem" -> {
                        val poem = poemViewModel.getPoemOfDay()
                        PoemScreen(poem, poemViewModel)
                    }
                    "poet" -> {
                        val poetViewModel: PoetViewModel = viewModel()
                        PoetScreen(poetViewModel)
                    }
                    "saved" -> {
                        SavedScreen(poemViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun FuturisticNavItem(selected: Boolean, onClick: () -> Unit, icon: ImageVector) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(60.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(if (selected) 32.dp else 24.dp),
                tint = if (selected) ElectricCyan else BorderWhite
            )
            if (selected) {
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(4.dp)
                        .clip(CircleShape)
                        .background(ElectricCyan)
                )
            }
        }
    }
}

@Composable
fun SavedScreen(viewModel: PoemViewModel) {
    val favorites = viewModel.favoritePoems

    Box(modifier = Modifier.fillMaxSize().background(SpaceBlack)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(SoftViolet.copy(alpha = 0.2f), Color.Transparent)
                    )
                )
        )

        Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
            Text(
                text = "YOUR VAULT",
                style = MaterialTheme.typography.labelLarge,
                color = ElectricCyan
            )
            Text(
                text = "Saved Gems",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 40.sp),
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (favorites.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        "No poems saved yet.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(favorites) { poem ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(24.dp))
                                .border(1.dp, BorderWhite, RoundedCornerShape(24.dp))
                                .clickable { /* Navigate to detail if needed */ },
                            color = Color.White.copy(alpha = 0.05f)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = poem.kannadaTitle,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White
                                )
                                Text(
                                    text = poem.kannadaPoet,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = ElectricCyan
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
