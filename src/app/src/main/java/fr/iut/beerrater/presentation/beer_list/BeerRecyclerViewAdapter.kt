package fr.iut.beerrater.presentation.beer_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.iut.beerrater.R
import fr.iut.beerrater.databinding.BeerItemCardBinding
import fr.iut.beerrater.domain.model.Beer

class BeerRecyclerViewAdapter(private val listener: BeerViewHolderListener) :
    ListAdapter<Beer, BeerRecyclerViewAdapter.BeerViewHolder>(DiffUtilBeerCallback) {

    private object DiffUtilBeerCallback : DiffUtil.ItemCallback<Beer>() {
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer) = oldItem.beerId == newItem.beerId
        override fun areContentsTheSame(oldItem: Beer, newItem: Beer) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BeerViewHolder(
            BeerItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) =
        holder.bind(getItem(position))


    class BeerViewHolder(
        private val binding: BeerItemCardBinding,
        listener: BeerViewHolderListener
    ) : RecyclerView.ViewHolder(binding.root) {
        val beer: Beer? get() = binding.beer

        init {
            itemView.setOnClickListener {
                beer?.let {
                    listener.onBeerSelected(it.beerId)
                }
            }
            /*Picasso.get()
                .load(beer?.imageUrl)
                .placeholder(R.drawable.ic_beer)
                .fit()
                .centerCrop()
                .into(binding.beerImage)*/
        }

        fun bind(beer: Beer) {
            binding.beer = beer
            binding.executePendingBindings()
        }
    }

    interface BeerViewHolderListener {
        fun onBeerSelected(beerId: Int)
    }
}