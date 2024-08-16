package com.akansha.digitalsurgery.networking.model

import com.google.gson.annotations.SerializedName

data class Procedure(
    @SerializedName("uuid") val id: String,
    @SerializedName("icon") val image: Image,
    @SerializedName("name") val title: String,
    @SerializedName("phases") val phases: List<String>,
)
