package com.akansha.digitalsurgery.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "phases",
    foreignKeys = [ForeignKey(
        entity = ProcedureDetailCard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("procedure_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PhaseDetailCard(
    @PrimaryKey(true) val id: Int = 0,
    @ColumnInfo("procedure_id") val procedureId: String,
    @ColumnInfo("image_url") val imageUrl: String,
    @ColumnInfo("name") val name: String,
)