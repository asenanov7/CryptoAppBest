package com.example.di

import android.app.Application
import android.content.Context
import com.example.data.RepositoryImpl
import com.example.data.database.room.DatabaseCoins
import com.example.data.database.room.DatabaseCoinsDao
import com.example.data.network.retrofit.ApiFactory
import com.example.data.network.retrofit.ApiService
import com.example.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
     fun bindRepository(impl: RepositoryImpl): Repository

     companion object {

         @ApplicationScope
         @Provides
         fun provideDatabaseCoinsDao(application: Application): DatabaseCoinsDao {
             return DatabaseCoins.getInstance(application).getDao()
         }

         @ApplicationScope
         @Provides
         fun provideApiService(): ApiService {
             return ApiFactory.apiService
         }

     }

}