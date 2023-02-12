package fr.iut.beerrater.presentation.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.databinding.InverseMethod
import fr.iut.beerrater.R
import fr.iut.beerrater.domain.model.Volume
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Converters {
    private val simpleFormater = SimpleDateFormat("MM/yyyy")
    private val complexFormater = SimpleDateFormat("dd MMMM yyyy HH:mm:ss")

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
        value?.let { simpleFormater.format(it) }
            ?: context.getString(R.string.unkown_first_brewed_date)

    @JvmStatic
    fun dateToLongString(context: Context, value: Date?): String {
        return value?.let { complexFormater.format(it) }
            ?: context.getString(R.string.unkown_comment_date_posted)
    }


    @JvmStatic
    fun abvToColor(context: Context, abv: Float): Int {
        val startColor = ContextCompat.getColor(context, R.color.lessAlcoholColor)
        val endColor = ContextCompat.getColor(context, R.color.mostAlcoholColor)
        return ColorUtils.blendARGB(startColor, endColor, abv / 5)
    }

    @JvmStatic
    @InverseMethod("intToFloat")
    fun floatToInt(value: Float) = value.toInt()

    @JvmStatic
    fun intToFloat(value: Int) = value.toFloat()

    @JvmStatic
    fun stringToFloat(value: String) = if (value.isBlank()) 0f else value.toFloat()

    @JvmStatic
    fun floatToString(value: Float) = value.toString()

    @JvmStatic
    fun booleanToVisibility(value: Boolean) = if (!value) View.GONE else View.VISIBLE
}