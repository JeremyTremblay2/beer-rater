package fr.iut.beerrater.presentation.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import fr.iut.beerrater.R
import fr.iut.beerrater.domain.model.Volume
import java.text.SimpleDateFormat
import java.util.*

object BeerListConverters {
    private val format = SimpleDateFormat("MM/yyyy", Locale.US)

    @JvmStatic
    fun volumeToString(context: Context, value: Volume?) = value?.let {
        value.value.toString() + when (value.unit) {
            Volume.VolumeUnit.GALLON -> context.getString(R.string.gallons_unit)
            Volume.VolumeUnit.LITER -> context.getString(R.string.liter_unit)
            else -> context.getString(R.string.eclamation_points)
        }
    } ?: context.getString(R.string.eclamation_points)

    @JvmStatic
    fun dateToString(context: Context, value: Date?) =
        value?.let { format.format(it) }
            ?: context.getString(R.string.unkown_first_brewed_date)

    @JvmStatic
    fun abuToColor(context: Context, abv: Float): Int {
        val startColor = ContextCompat.getColor(context, R.color.lessAlcoholColor)
        val endColor = ContextCompat.getColor(context, R.color.mostAlcoholColor)
        return ColorUtils.blendARGB(startColor, endColor, abv / 5)
    }
}