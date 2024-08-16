package com.akansha.digitalsurgery.di

import com.akansha.digitalsurgery.Constants
import com.akansha.digitalsurgery.networking.ProcedureRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun procedureApi(retrofit: Retrofit): ProcedureRetrofitService =
        retrofit.create(ProcedureRetrofitService::class.java)
}