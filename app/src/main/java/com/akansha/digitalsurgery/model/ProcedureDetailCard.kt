package com.akansha.digitalsurgery.model

import com.akansha.digitalsurgery.Constants.DEFAULT_BOOL
import com.akansha.digitalsurgery.Constants.DEFAULT_INT
import com.akansha.digitalsurgery.Constants.DEFAULT_STRING

data class ProcedureDetailCard(
    val id: String = DEFAULT_STRING,
    val cardImageUrl: String = DEFAULT_STRING,
    val title: String = DEFAULT_STRING,
    val duration: Int = DEFAULT_INT,
    val creationDate: String = DEFAULT_STRING,
    val phases: List<PhaseDetailCard> = emptyList(),
    var isFavourite: Boolean = DEFAULT_BOOL,
)