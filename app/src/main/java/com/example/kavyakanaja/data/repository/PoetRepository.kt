package com.example.kavyakanaja.data.repository

import android.content.Context
import com.example.kavyakanaja.data.model.Poet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PoetRepository(private val context: Context) {

    fun getPoets(): List<Poet> {
        val json = context.assets.open("poets.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<Poet>>() {}.type
        return Gson().fromJson(json, type)
    }
}