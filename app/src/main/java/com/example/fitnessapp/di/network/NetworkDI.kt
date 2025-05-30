package com.example.fitnessapp.di.network

import com.example.fitnessapp.data.network.FitnessBackendObject
import com.example.fitnessapp.data.network.FitnessBackendService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkDI {
    @Provides
    @Singleton
    fun provideFitnessBackendService(): FitnessBackendService = FitnessBackendObject.retrofit.create(FitnessBackendService::class.java)
}