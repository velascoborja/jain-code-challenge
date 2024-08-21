package com.akansha.digitalsurgery.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "procedures")
data class ProcedureItem(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo val title: String,
    @ColumnInfo(name = "phase_count") val phaseCount: Int,
)
