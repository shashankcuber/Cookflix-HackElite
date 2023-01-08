package com.android.ranit.cookflix.domain.usecase

import com.android.ranit.cookflix.data.model.response.RecommendationResponse
import com.android.ranit.cookflix.data.utils.Resource
import com.android.ranit.cookflix.domain.repository.CookflixRepository

class GetRecommendationUseCase(private val cookflixRepository: CookflixRepository) {
    suspend fun execute(ingredientList: String): Resource<RecommendationResponse> {
        return cookflixRepository.getRecommendations(ingredientList)
    }
}