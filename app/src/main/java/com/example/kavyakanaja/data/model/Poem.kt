package com.example.kavyakanaja.data.model

data class Poem(
    val id: String,
    val title: String,
    val kannadaTitle: String,
    val poet: String,
    val kannadaPoet: String,
    val verse: String,
    val meanings: Map<String, String>,
    val description: String,
    val category: String
)