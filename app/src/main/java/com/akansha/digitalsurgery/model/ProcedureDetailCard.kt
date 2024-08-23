package com.akansha.digitalsurgery.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "procedure_details",
    foreignKeys = [ForeignKey(
        entity = ProcedureItem::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ProcedureDetailCard(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "card_image_url") val cardImageUrl: String = "",
    @ColumnInfo val title: String = "",
    @ColumnInfo val duration: Int = 0,
    @ColumnInfo(name = "creation_Date") val creationDate: String = "",
    @ColumnInfo val phases: List<PhaseDetailCard> = emptyList(),
    @ColumnInfo var isFavourite: Boolean = false,
)