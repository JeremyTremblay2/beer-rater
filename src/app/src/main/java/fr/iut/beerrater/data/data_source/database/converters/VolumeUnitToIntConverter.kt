package fr.iut.beerrater.data.data_source.database.converters

import androidx.room.TypeConverter
import fr.iut.beerrater.domain.model.Volume

fun Int.toVolume() = enumValues<Volume.VolumeUnit>()[this]

class VolumeUnitToIntConverter {
    @TypeConverter
    fun fromInt(ordinal: Int) = ordinal.toVolume()

    @TypeConverter
    fun toOrdinal(volume: Volume.VolumeUnit) = volume.ordinal
}