package fr.iut.beerrater.data.data_source.api.dto

import com.google.gson.annotations.SerializedName
import fr.iut.beerrater.domain.model.Volume

data class VolumeDTO(
    val unit: VolumeUnitDTO,
    val value: Int
) {
    enum class VolumeUnitDTO {
        @SerializedName("litres")
        LITER,
        @SerializedName("gallons")
        GALLON
    }
}

fun VolumeDTO.toVolume(): Volume {
    return Volume(
        0,
        unit = enumValueOf<Volume.VolumeUnit>(unit.name),
        value = value
    )
}