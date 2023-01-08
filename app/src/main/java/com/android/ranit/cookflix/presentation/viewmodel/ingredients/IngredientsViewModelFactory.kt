package com.android.ranit.cookflix.presentation.viewmodel.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.ranit.cookflix.domain.usecase.GetIngredientsUseCase

class IngredientsViewModelFactory(
    private val getIngredientsUseCase: GetIngredientsUseCase
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return IngredientsViewModel(
            getIngredientsUseCase
        ) as T
    }
}