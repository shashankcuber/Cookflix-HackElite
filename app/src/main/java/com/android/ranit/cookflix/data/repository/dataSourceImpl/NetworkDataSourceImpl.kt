package com.android.ranit.cookflix.data.repository.dataSourceImpl

import com.android.ranit.cookflix.data.api.ApiService
import com.android.ranit.cookflix.data.model.response.FoodItemsResponse
import com.android.ranit.cookflix.data.model.response.IngredientResponse
import com.android.ranit.cookflix.data.model.response.RecommendationResponse
import com.android.ranit.cookflix.data.repository.dataSource.NetworkDataSource
import retrofit2.Response

class NetworkDataSourceImpl(private val apiService: ApiService): NetworkDataSource {

    override suspend fun getFoodItems(): Response<FoodItemsResponse> {
       return apiService.getFoodItems()
    }

    override suspend fun getIngredients(): Response<IngredientResponse> {
        return apiService.getIngredients()
    }

    override suspend fun getRecommendations(ingredientsList: String): Response<RecommendationResponse> {
        return apiService.getRecommendations(ingredientsList)
    }
}