package fr.iut.beerrater.presentation.beers_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import fr.iut.beerrater.R
import fr.iut.beerrater.domain.model.Beer
import java.text.SimpleDateFormat
import java.util.*

class BeerRecyclerViewAdapter(private var beers: List<Beer>) :
    RecyclerView.Adapter<BeerRecyclerViewAdapter.BeerViewHolder>() {

    override fun getItemCount() = beers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BeerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.beer_item_card,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) =
        holder.bind(beers[position])


    class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val format = SimpleDateFormat("MM/yyyy", Locale.US)
        private val beerImage = itemView.findViewById<ImageView>(R.id.beer_image)
        private val beerName = itemView.findViewById<TextView>(R.id.beer_name)
        private val beerTagline = itemView.findViewById<TextView>(R.id.beer_tagline)
        private val beerVolume = itemView.findViewById<TextView>(R.id.beer_volume)
        private val beerBrewedDate = itemView.findViewById<TextView>(R.id.beer_brewed_date)
        private val beerCardview = itemView.findViewById<CardView>(R.id.beer_cardview)

        var beer: Beer? = null
            private set

        fun bind(beer: Beer) {
            val context = itemView.context
            this.beer = beer
            beerName.text = beer.name
            beerTagline.text = beer.tagline
            beerVolume.text = beer.volume?.toString() ?: context.getString(R.string.eclamation_points)
            beerBrewedDate.text = beer.firstBrewedDate?.let { format.format(it) }
                ?: context.getString(R.string.unkown_first_brewed_date)
            val startColor = ContextCompat.getColor(context, R.color.lessAlcoholColor)
            val endColor = ContextCompat.getColor(context, R.color.mostAlcoholColor)
            val color = ColorUtils.blendARGB(startColor, endColor, beer.abv / 5)
            beerCardview.setCardBackgroundColor(color)
        }
    }

    public fun updateList(beers: List<Beer>) {
        this.beers = beers;
        notifyDataSetChanged()
    }
}