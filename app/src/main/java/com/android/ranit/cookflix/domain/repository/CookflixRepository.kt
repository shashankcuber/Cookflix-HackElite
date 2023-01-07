package com.android.ranit.cookflix.domain.repository

import com.android.ranit.cookflix.data.model.response.FoodItemsResponse
import com.android.ranit.cookflix.data.model.response.IngredientsResponse
import com.android.ranit.cookflix.data.utils.Resource

interface CookflixRepository {
    suspend fun getFoodItems(): Resource<FoodItemsResponse>
    suspend fun getIngredients(): Resource<IngredientsResponse>
}