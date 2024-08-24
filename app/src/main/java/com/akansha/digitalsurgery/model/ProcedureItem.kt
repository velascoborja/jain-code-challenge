package com.akansha.digitalsurgery.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.akansha.digitalsurgery.Constants.DEFAULT_BOOL

@Entity(tableName = "procedures")
data class ProcedureItem(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo val title: String,
    @ColumnInfo(name = "phase_count") val phaseCount: Int,
    @ColumnInfo var isFavourite: Boolean = DEFAULT_BOOL,
)
