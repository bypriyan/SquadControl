package com.bypriyan.ostiamare.util

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromListToString(list: List<Int>?): String {
        return list?.joinToString(",") ?: ""
    }

    @TypeConverter
    fun fromStringToList(data: String?): List<Int> {
        return data?.split(",")?.map { it.toInt() } ?: listOf()
    }
}