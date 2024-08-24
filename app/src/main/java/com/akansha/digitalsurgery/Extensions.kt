package com.akansha.digitalsurgery

import android.os.Build
import androidx.annotation.RequiresApi
import com.akansha.digitalsurgery.model.ProcedureDetailCard
import com.akansha.digitalsurgery.model.ProcedureItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun Long.toMinutes(): Int = (this / SECONDS_IN_ONE_MINUTE).toInt()

@RequiresApi(Build.VERSION_CODES.O)
fun String.format(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSSSSS]")
    val inputDate: LocalDateTime = LocalDateTime.parse(this, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return inputDate.format(outputFormatter)
}

fun List<ProcedureItem>.addFavouritesInfo(favoritesList: List<ProcedureItem>): List<ProcedureItem> {
    val favoriteIds = favoritesList.map { it.id }
    return this.onEach {
        it.isFavourite = favoriteIds.contains(it.id)
    }
}

fun ProcedureDetailCard.addFavouritesInfo(favoritesList: List<ProcedureItem>): ProcedureDetailCard {
    val favoriteIds = favoritesList.map { it.id }
    val updatedDetail = this.apply {
        this.isFavourite = favoriteIds.contains(this.id)
    }
    return updatedDetail
}

private const val SECONDS_IN_ONE_MINUTE = 60