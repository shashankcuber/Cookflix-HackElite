package com.android.ranit.cookflix.data.model.response

data class Recommendation(
    val image: String,
    val ingredients: String,
    val recipe: String,
    val recipe_name: String,
    val score: String
)