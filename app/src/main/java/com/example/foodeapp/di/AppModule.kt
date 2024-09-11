package com.example.foodeapp.di

import com.example.foodeapp.data.datasource.FoodEDataSource
import com.example.foodeapp.data.repo.FoodERepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodEDataSource(): FoodEDataSource{
        return  FoodEDataSource()
    }

    @Provides
    @Singleton
    fun provideFoodERepository(fds: FoodEDataSource): FoodERepository{
        return FoodERepository(fds)
    }

}