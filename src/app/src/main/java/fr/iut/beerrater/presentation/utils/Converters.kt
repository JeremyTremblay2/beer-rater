package fr.iut.beerrater.presentation.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import fr.iut.beerrater.R
import fr.iut.beerrater.domain.model.Volume
import java.text.SimpleDateFormat
import java.util.*

object Converters {
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
    fun abvToColor(context: Context, abv: Float): Int {
        val startColor = ContextCompat.getColor(context, R.color.lessAlcoholColor)
        val endColor = ContextCompat.getColor(context, R.color.mostAlcoholColor)
        return ColorUtils.blendARGB(startColor, endColor, abv / 5)
    }

    @JvmStatic
    fun floatToInt(value: Float) = value.toInt()

    @JvmStatic
    fun floatToString(value: Float) = value.toString()

    @JvmStatic
    fun booleanToVisibility(value: Boolean) = if (!value) View.GONE else View.VISIBLE
}