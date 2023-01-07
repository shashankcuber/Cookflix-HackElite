package com.android.ranit.cookflix.presentation.di

import com.android.ranit.cookflix.data.api.ApiService
import com.android.ranit.cookflix.data.repository.dataSource.NetworkDataSource
import com.android.ranit.cookflix.data.repository.dataSourceImpl.NetworkDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltNetworkDataModule {

    @Singleton
    @Provides
    fun provideNetworkDataSource(apiService: ApiService) : NetworkDataSource {
        return NetworkDataSourceImpl(apiService)
    }
}