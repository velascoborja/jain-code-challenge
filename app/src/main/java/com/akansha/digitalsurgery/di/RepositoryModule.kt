package com.akansha.digitalsurgery.di

import com.akansha.digitalsurgery.repository.IProcedureRepository
import com.akansha.digitalsurgery.repository.ProcedureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun repository(repository: ProcedureRepository): IProcedureRepository
}