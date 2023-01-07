package com.android.ranit.cookflix.domain.usecase

import com.android.ranit.cookflix.data.model.response.FoodItemsResponse
import com.android.ranit.cookflix.data.utils.Resource
import com.android.ranit.cookflix.domain.repository.CookflixRepository

class GetFoodItemsUseCase(private val cookflixRepository: CookflixRepository) {
    suspend fun execute(): Resource<FoodItemsResponse> {
        return cookflixRepository.getFoodItems()
    }
}