package com.akansha.digitalsurgery.datastorage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akansha.digitalsurgery.model.PhaseDetailCard
import com.akansha.digitalsurgery.model.ProcedureDetailCard
import com.akansha.digitalsurgery.model.ProcedureItem

@Database(
    entities = [ProcedureItem::class, ProcedureDetailCard::class, PhaseDetailCard::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ProcedureDatabase : RoomDatabase() {
    abstract fun procedureDao(): ProcedureDao
}