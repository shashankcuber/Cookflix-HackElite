package com.android.ranit.cookflix.presentation.di

import com.android.ranit.cookflix.data.repository.CookflixRepositoryImpl
import com.android.ranit.cookflix.data.repository.dataSource.NetworkDataSource
import com.android.ranit.cookflix.domain.repository.CookflixRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltRepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(networkDataSource: NetworkDataSource) : CookflixRepository {
        return CookflixRepositoryImpl(networkDataSource)
    }
}