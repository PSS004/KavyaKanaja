package com.example.kavyakanaja.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.kavyakanaja.data.model.Poet
import com.example.kavyakanaja.data.repository.PoetRepository
import com.example.kavyakanaja.data.repository.PoemRepository

class PoetViewModel(application: Application) : AndroidViewModel(application) {

    private val poetRepo = PoetRepository(application)
    private val poemRepo = PoemRepository(application)

    val poets: List<Poet>

    init {
        poets = poetRepo.getPoets()
    }
}