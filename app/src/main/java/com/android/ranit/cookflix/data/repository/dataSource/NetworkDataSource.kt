package com.android.ranit.cookflix.data.repository.dataSource

import com.android.ranit.cookflix.data.model.response.FoodItemsResponse
import com.android.ranit.cookflix.data.model.response.IngredientResponse
import com.android.ranit.cookflix.data.model.response.RecommendationResponse
import retrofit2.Response

interface NetworkDataSource {
    suspend fun getFoodItems(): Response<FoodItemsResponse>
    suspend fun getIngredients(): Response<IngredientResponse>
    suspend fun getRecommendations(ingredientsList: String): Response<RecommendationResponse>
}