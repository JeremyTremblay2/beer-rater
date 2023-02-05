package fr.iut.beerrater.data.data_source.api.dto

import com.google.gson.annotations.SerializedName
import fr.iut.beerrater.domain.model.Beer
import java.util.*

data class BeerDTO(
    val abv: Float,
    val attenuation_level: Double,
    val boil_volume: BoilVolumeDTO,
    val brewers_tips: String,
    @SerializedName("contributed_by") val contributor: String,
    val description: String,
    val ebc: Int,
    val first_brewed: Date,
    val food_pairing: List<String>,
    val ibu: Double,
    val id: Int,
    val image_url: String,
    val ingredients: IngredientsDTO,
    val method: MethodDTO,
    val name: String,
    val ph: Float,
    @SerializedName("srm") val srm: Double,
    val tagline: String,
    val target_fg: Int,
    val target_og: Double,
    val volume: VolumeDTO
)

fun BeerDTO.toBeer(): Beer {
    return Beer(
        beerId = id,
        name = name,
        tagline = tagline,
        firstBrewedDate = first_brewed,
        description = description,
        imageUrl = image_url,
        ph = ph,
        abv = abv,
        volume = volume.toVolume(),
        contributor = contributor
    )
}