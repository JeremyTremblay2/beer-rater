package fr.iut.beerrater.presentation.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import fr.iut.beerrater.R
import java.lang.Exception

private const val PICASSO_TAG = "Picasso"

@BindingAdapter("imageUrl")
fun bindUrlImage(imageView: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        val picasso = Picasso.get()
        //picasso.setIndicatorsEnabled(true) // For debug purposes.
        picasso.load(imageUrl)
            .placeholder(R.drawable.ic_bar)
            .networkPolicy(NetworkPolicy.OFFLINE) // First load the cache
            .into(imageView, object : Callback {
                override fun onSuccess() {}
                override fun onError(e: Exception?) {
                    Picasso.get()
                        .load(imageUrl)    // Then check online.
                        .placeholder(R.drawable.ic_bar)
                        .into(imageView, object : Callback {
                            override fun onSuccess() {}
                            override fun onError(e: Exception?) {
                                Log.i(PICASSO_TAG, "Could not fetch image from url: $imageUrl. Error: ${e?.message}")
                            }
                        })
                }
            })
    }
    else {
        Log.i(PICASSO_TAG, "Could not load a null image.")
        imageView.setImageResource(R.drawable.ic_bar)
    }
}