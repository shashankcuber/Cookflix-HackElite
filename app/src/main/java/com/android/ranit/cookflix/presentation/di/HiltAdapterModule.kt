package com.android.ranit.cookflix.presentation.di

import com.android.ranit.cookflix.presentation.ui.adapter.FoodItemsAdapter
import com.android.ranit.cookflix.presentation.ui.adapter.IngredientsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class HiltAdapterModule {

    @Provides
    fun providesFoodItemsAdapter(): FoodItemsAdapter {
        return FoodItemsAdapter()
    }

    @Provides
    fun provideIngredientsAdapter(): IngredientsAdapter {
        return IngredientsAdapter()
    }
}