package com.akansha.digitalsurgery.networking.model

import com.google.gson.annotations.SerializedName

data class Phase(
    @SerializedName("uuid") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("icon") val image: Image,
)
