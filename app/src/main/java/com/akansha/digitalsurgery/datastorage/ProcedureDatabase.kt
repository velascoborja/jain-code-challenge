package com.akansha.digitalsurgery.datastorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akansha.digitalsurgery.model.ProcedureItem

@Database(
    entities = [ProcedureItem::class],
    version = 1
)
abstract class ProcedureDatabase : RoomDatabase() {
    abstract fun procedureDao(): ProcedureDao
}