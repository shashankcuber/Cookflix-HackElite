package com.android.ranit.cookflix.presentation.di

import com.android.ranit.cookflix.domain.repository.CookflixRepository
import com.android.ranit.cookflix.domain.usecase.GetFoodItemsUseCase
import com.android.ranit.cookflix.domain.usecase.GetIngredientsUseCase
import com.android.ranit.cookflix.domain.usecase.GetRecommendationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltUseCaseModule {

    @Singleton
    @Provides
    fun provideGetFoodItemsUseCase(cookflixRepository: CookflixRepository)
            : GetFoodItemsUseCase {
        return GetFoodItemsUseCase(cookflixRepository)
    }

    @Singleton
    @Provides
    fun provideGetIngredientsUseCase(cookflixRepository: CookflixRepository)
            : GetIngredientsUseCase {
        return GetIngredientsUseCase(cookflixRepository)
    }

    @Singleton
    @Provides
    fun provideGetRecommendationsUseCase(cookflixRepository: CookflixRepository)
            : GetRecommendationUseCase {
        return GetRecommendationUseCase(cookflixRepository)
    }
}