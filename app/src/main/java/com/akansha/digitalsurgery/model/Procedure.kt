package com.akansha.digitalsurgery.model

import com.google.gson.annotations.SerializedName

data class Procedure(
    @SerializedName("icon") val image: Image,
    @SerializedName("name") val title: String,
    @SerializedName("phases") val phases: List<String>,
)
