package com.android.ranit.cookflix.presentation.viewmodel.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.ranit.cookflix.domain.usecase.GetIngredientsUseCase
import com.android.ranit.cookflix.domain.usecase.GetRecommendationUseCase

class IngredientsViewModelFactory(
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val getRecommendationUseCase: GetRecommendationUseCase
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return IngredientsViewModel(
            getIngredientsUseCase,
            getRecommendationUseCase
        ) as T
    }
}