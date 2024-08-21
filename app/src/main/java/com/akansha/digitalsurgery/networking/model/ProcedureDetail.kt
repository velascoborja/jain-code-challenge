package com.akansha.digitalsurgery.networking.model

import com.google.gson.annotations.SerializedName

data class ProcedureDetail(
    @SerializedName("uuid") val id: String,
    @SerializedName("name") val title: String,
    @SerializedName("card") val card: Image,
    @SerializedName("duration") val duration: Long,
    @SerializedName("date_published") val creationDate: String,
    @SerializedName("phases") val phases: List<Phase>,
)