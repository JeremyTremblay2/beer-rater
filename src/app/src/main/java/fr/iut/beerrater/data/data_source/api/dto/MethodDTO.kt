package fr.iut.beerrater.data.data_source.api.dto

data class MethodDTO(
    val fermentation: FermentationDTO,
    val mash_temp: List<MashTempDTO>,
    val twist: String
)