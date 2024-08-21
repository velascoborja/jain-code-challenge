package com.akansha.digitalsurgery.di

import android.content.Context
import androidx.room.Room
import com.akansha.digitalsurgery.datastorage.ProcedureDao
import com.akansha.digitalsurgery.datastorage.ProcedureDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun database(@ApplicationContext appContext: Context): ProcedureDatabase {
        return Room.databaseBuilder(
            appContext,
            ProcedureDatabase::class.java,
            "procedure_db"
        ).build()
    }

    @Provides
    fun dao(database: ProcedureDatabase): ProcedureDao {
        return database.procedureDao()
    }
}