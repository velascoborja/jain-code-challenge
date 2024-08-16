package com.akansha.digitalsurgery.repository.mapper

import com.akansha.digitalsurgery.model.ProcedureItem
import com.akansha.digitalsurgery.networking.model.Procedure

fun List<Procedure>.map(): List<ProcedureItem> {
    return this.map {
        ProcedureItem(
            imageUrl = it.image.url,
            title = it.title,
            phaseCount = it.phases.size,
        )
    }
}
