package com.cscorner.newsx.db

import androidx.room.TypeConverter
import com.cscorner.newsx.models.Source
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String): Source {
        return Gson().fromJson(sourceString, Source::class.java)
    }
}
