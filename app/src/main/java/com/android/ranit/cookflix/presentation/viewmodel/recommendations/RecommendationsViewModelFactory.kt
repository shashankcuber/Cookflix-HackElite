package com.android.ranit.cookflix.presentation.viewmodel.recommendations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.ranit.cookflix.domain.usecase.GetRecommendationUseCase

class RecommendationsViewModelFactory(
    private val getRecommendationUseCase: GetRecommendationUseCase
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecommendationsViewModel(
            getRecommendationUseCase
        ) as T
    }
}