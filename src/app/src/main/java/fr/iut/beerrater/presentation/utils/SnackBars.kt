package fr.iut.beerrater.presentation.utils

import android.app.Activity
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.color.MaterialColors
import com.google.android.material.snackbar.Snackbar
import fr.iut.beerrater.R

fun showSnackBar(view: View, message: String, action: String? = null,
                 actionListener: View.OnClickListener? = null, duration: Int = Snackbar.LENGTH_SHORT) {
    val snackBar = Snackbar.make(view, message, duration)
        .setTextColor(MaterialColors.getColor(view.context, com.google.android.material.R.attr.colorOnSurface,
            ContextCompat.getColor(view.context, R.color.onSurface)))
        .setBackgroundTint(MaterialColors.getColor(view.context, com.google.android.material.R.attr.colorSurface,
            ContextCompat.getColor(view.context, R.color.surface)))

    if (action != null && actionListener != null) {
        snackBar.setAction(action, actionListener)
            .setActionTextColor(MaterialColors.getColor(view.context, com.google.android.material.R.attr.colorPrimaryVariant,
                ContextCompat.getColor(view.context, R.color.primaryVariant)))
    }
    snackBar.show()
}