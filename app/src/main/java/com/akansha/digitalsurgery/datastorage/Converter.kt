package com.akansha.digitalsurgery.datastorage

import androidx.room.TypeConverter
import com.akansha.digitalsurgery.model.PhaseDetailCard
import com.akansha.digitalsurgery.networking.model.Phase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromPhaseDetailCardList(phases: List<PhaseDetailCard>): String {
        return Gson().toJson(phases)
    }

    @TypeConverter
    fun toPhaseDetailCardList(phasesString: String): List<PhaseDetailCard> {
        val listType = object : TypeToken<List<PhaseDetailCard>>() {}.type
        return Gson().fromJson(phasesString, listType)
    }
}