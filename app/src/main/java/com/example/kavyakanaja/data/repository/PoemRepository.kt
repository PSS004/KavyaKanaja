package com.example.kavyakanaja.data.repository

import android.content.Context
import com.example.kavyakanaja.data.model.Poem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PoemRepository(private val context: Context) {

    fun getPoems(): List<Poem> {
        val json = context.assets.open("poems.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<Poem>>() {}.type
        return Gson().fromJson(json, type)
    }
}