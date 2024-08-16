package com.akansha.digitalsurgery.networking.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("url") val url: String,
    @SerializedName("version") val version: Int,
)