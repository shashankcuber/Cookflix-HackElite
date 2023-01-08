package com.android.ranit.cookflix.domain.repository

import com.android.ranit.cookflix.data.model.response.FoodItemsResponse
import com.android.ranit.cookflix.data.model.response.IngredientResponse
import com.android.ranit.cookflix.data.model.response.RecommendationResponse
import com.android.ranit.cookflix.data.utils.Resource

interface CookflixRepository {
    suspend fun getFoodItems(): Resource<FoodItemsResponse>
    suspend fun getIngredients(): Resource<IngredientResponse>
    suspend fun getRecommendations(ingredientsList: String): Resource<RecommendationResponse>
}