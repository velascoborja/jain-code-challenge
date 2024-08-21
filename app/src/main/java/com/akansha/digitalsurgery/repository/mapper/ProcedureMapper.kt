package com.akansha.digitalsurgery.repository.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.akansha.digitalsurgery.format
import com.akansha.digitalsurgery.model.PhaseDetailCard
import com.akansha.digitalsurgery.model.ProcedureDetailCard
import com.akansha.digitalsurgery.model.ProcedureItem
import com.akansha.digitalsurgery.networking.model.Procedure
import com.akansha.digitalsurgery.networking.model.ProcedureDetail
import com.akansha.digitalsurgery.toMinutes

fun List<Procedure>.map(): List<ProcedureItem> {
    return this.map {
        ProcedureItem(
            id = it.id,
            imageUrl = it.image.url,
            title = it.title,
            phaseCount = it.phases.size,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun ProcedureDetail.map(): ProcedureDetailCard {
    return ProcedureDetailCard(
        id = id,
        cardImageUrl = card.url,
        title = title,
        duration = duration.toMinutes(),
        creationDate = creationDate.format(),
        phases = phases.map {
            PhaseDetailCard(
                procedureId = id,
                imageUrl = it.image.url,
                name = it.name
            )
        }
    )
}
