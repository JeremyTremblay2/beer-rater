package fr.iut.beerrater.data.data_source.api.dto

data class IngredientsDTO(
    val hops: List<HopDTO>,
    val malt: List<MaltDTO>,
    val yeast: String
)