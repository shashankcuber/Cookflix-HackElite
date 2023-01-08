package com.android.ranit.cookflix.presentation.di

import com.android.ranit.cookflix.domain.usecase.GetFoodItemsUseCase
import com.android.ranit.cookflix.domain.usecase.GetIngredientsUseCase
import com.android.ranit.cookflix.domain.usecase.GetRecommendationUseCase
import com.android.ranit.cookflix.presentation.viewmodel.home.HomeViewModelFactory
import com.android.ranit.cookflix.presentation.viewmodel.ingredients.IngredientsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideHomeViewModelFactory(getFoodItemsUseCase: GetFoodItemsUseCase)
            : HomeViewModelFactory {
        return HomeViewModelFactory(getFoodItemsUseCase)
    }

    @Singleton
    @Provides
    fun provideIngredientsViewModelFactory(
        getIngredientsUseCase: GetIngredientsUseCase,
        getRecommendationUseCase: GetRecommendationUseCase
    ): IngredientsViewModelFactory {
        return IngredientsViewModelFactory(getIngredientsUseCase, getRecommendationUseCase)
    }
}