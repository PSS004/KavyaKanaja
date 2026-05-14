package com.example.kavyakanaja.data.repository

import android.content.Context
import android.content.SharedPreferences

class FavoritesRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)

    fun isFavorite(id: String): Boolean {
        return prefs.getBoolean(id, false)
    }

    fun toggleFavorite(id: String) {
        val current = isFavorite(id)
        prefs.edit().putBoolean(id, !current).apply()
    }

    fun getFavoriteIds(): Set<String> {
        return prefs.all.filter { it.value is Boolean && it.value == true }.keys
    }
}
