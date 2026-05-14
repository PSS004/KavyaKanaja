package com.example.kavyakanaja.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.kavyakanaja.data.model.Poem
import com.example.kavyakanaja.data.repository.PoemRepository
import com.example.kavyakanaja.data.repository.FavoritesRepository
import java.util.Calendar

class PoemViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = PoemRepository(application)
    private val favoritesRepo = FavoritesRepository(application)
    val allPoems = repo.getPoems()
    
    val favoritePoems = mutableStateListOf<Poem>()

    init {
        updateFavorites()
    }

    fun getPoemOfDay(): Poem {
        val day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        return allPoems[day % allPoems.size]
    }

    fun isFavorite(id: String) = favoritesRepo.isFavorite(id)

    fun toggleFavorite(id: String) {
        favoritesRepo.toggleFavorite(id)
        updateFavorites()
    }

    private fun updateFavorites() {
        favoritePoems.clear()
        val ids = favoritesRepo.getFavoriteIds()
        favoritePoems.addAll(allPoems.filter { ids.contains(it.id) })
    }
}
