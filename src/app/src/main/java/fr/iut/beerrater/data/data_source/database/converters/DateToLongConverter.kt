package fr.iut.beerrater.data.data_source.database.converters

import androidx.room.TypeConverter
import java.util.Date

class DateToLongConverter {
    @TypeConverter
    fun toDate(date: Long?) = date?.let { Date(it) }

    @TypeConverter
    fun fromDate(date: Date?) = date?.time
}
