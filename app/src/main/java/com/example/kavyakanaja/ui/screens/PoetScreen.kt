package com.example.kavyakanaja.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kavyakanaja.viewmodel.PoetViewModel
import com.example.kavyakanaja.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoetScreen(viewModel: PoetViewModel, modifier: Modifier = Modifier) {
    val poets = viewModel.poets
    var searchQuery by remember { mutableStateOf("") }
    
    val filteredPoets = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            poets
        } else {
            poets.filter { 
                it.name.contains(searchQuery, ignoreCase = true) || 
                it.kannadaName.contains(searchQuery) 
            }
        }
    }

    Box(modifier = modifier.fillMaxSize().background(SpaceBlack)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(DeepIndigo.copy(alpha = 0.5f), Color.Transparent)
                    )
                )
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "LITERARY MASTERS",
                    style = MaterialTheme.typography.labelLarge,
                    color = ElectricCyan
                )
                Text(
                    text = "Discover Legacy",
                    style = MaterialTheme.typography.displayLarge.copy(fontSize = 40.sp),
                    color = Color.White
                )
                
                Spacer(modifier = Modifier.height(24.dp))

                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(1.dp, BorderWhite, RoundedCornerShape(20.dp)),
                    placeholder = { Text("Search poets...", color = Color.White.copy(alpha = 0.5f)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = ElectricCyan) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.1f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.1f),
                        disabledContainerColor = Color.White.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    singleLine = true
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(filteredPoets) { poet ->
                    FuturisticPoetCard(poet = poet)
                }
            }
        }
    }
}

@Composable
fun FuturisticPoetCard(poet: com.example.kavyakanaja.data.model.Poet) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .border(1.dp, BorderWhite, RoundedCornerShape(32.dp)),
        color = Color.White.copy(alpha = 0.03f)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = poet.kannadaName,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Text(
                text = poet.name.uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = ElectricCyan,
                fontSize = 12.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = poet.bio,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f), // Increased brightness
                lineHeight = 22.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "ACHIEVEMENTS",
                style = MaterialTheme.typography.labelSmall,
                color = ElectricCyan,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            poet.achievements.forEach { achievement ->
                Text(
                    text = "• $achievement",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White, // Fully white for maximum visibility
                    modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                )
            }
        }
    }
}
