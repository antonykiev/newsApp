package com.pet.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pet.database.entity.SourceEntity

class Converters {

    @TypeConverter
    fun fromSource(source: SourceEntity): String {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(value: String): SourceEntity {
        val listType = object : TypeToken<SourceEntity>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToJson(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonToList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type)
    }

}