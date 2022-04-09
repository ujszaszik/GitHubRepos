package com.withintent.githubrepos.features.repos

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReposModule {

    @Provides
    @Singleton
    fun provideReposService(retrofit: Retrofit): ReposService {
        return retrofit.create(ReposService::class.java)
    }

}