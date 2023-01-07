package com.android.ranit.cookflix.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.ranit.cookflix.domain.usecase.GetFoodItemsUseCase

class HomeViewModelFactory(
    private val getFoodItemsUseCase: GetFoodItemsUseCase
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            getFoodItemsUseCase
        ) as T
    }
}