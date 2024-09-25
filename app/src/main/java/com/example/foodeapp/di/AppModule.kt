package com.example.foodeapp.di

import com.example.foodeapp.data.datasource.FoodEDataSource
import com.example.foodeapp.data.repo.FoodERepository
import com.example.foodeapp.retrofit.ApiUtils
import com.example.foodeapp.retrofit.FoodsDao
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
    fun provideFoodsDao(): FoodsDao{
        return ApiUtils.getFoodsDao()
    }

    @Provides
    @Singleton
    fun provideCollectionReference() : CollectionReference{
        return Firebase.firestore.collection("users")
    }

    @Provides
    @Singleton
    fun provideFoodEDataSource(fdao: FoodsDao, collectionUsers: CollectionReference): FoodEDataSource{
        return  FoodEDataSource(fdao, collectionUsers)
    }

    @Provides
    @Singleton
    fun provideFoodERepository(fds: FoodEDataSource): FoodERepository{
        return FoodERepository(fds)
    }

}