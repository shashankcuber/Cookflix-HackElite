package com.android.ranit.cookflix.data.model.response

import com.google.gson.annotations.SerializedName

data class IngredientResponse(
    @SerializedName("Ingredient_list")
    val ingredientList: List<String>?
)