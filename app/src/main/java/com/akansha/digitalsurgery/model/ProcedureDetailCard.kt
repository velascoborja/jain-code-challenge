package com.akansha.digitalsurgery.model

data class ProcedureDetailCard(
    val cardImageUrl: String = "",
    val title: String = "",
    val duration: Int = 0,
    val creationDate: String = "",
    val phases: List<PhaseDetailCard> = emptyList(),
)