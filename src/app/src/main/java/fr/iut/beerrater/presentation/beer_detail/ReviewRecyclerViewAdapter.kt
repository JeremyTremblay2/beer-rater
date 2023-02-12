package fr.iut.beerrater.presentation.beer_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.iut.beerrater.databinding.CommentItemCardBinding
import fr.iut.beerrater.domain.model.Review

class ReviewRecyclerViewAdapter(private val listener: ReviewViewHolderListener) :
    ListAdapter<Review, ReviewRecyclerViewAdapter.ReviewViewHolder>(DiffUtilReviewCallback) {

    private object DiffUtilReviewCallback : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review) = oldItem.reviewId == newItem.reviewId
        override fun areContentsTheSame(oldItem: Review, newItem: Review) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReviewViewHolder(
            CommentItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) =
        holder.bind(getItem(position))


    class ReviewViewHolder(
        private val binding: CommentItemCardBinding,
        private val listener: ReviewViewHolderListener
    ) : RecyclerView.ViewHolder(binding.root) {
        val review: Review? get() = binding.review

        init {
            itemView.setOnClickListener {
                review?.let {
                    listener.onReviewSelected(it.reviewId)
                }
            }
        }

        fun bind(review: Review) {
            binding.review = review
            binding.reviewItemDeleteButton.setOnClickListener {
                listener.onReviewDeleted(review)
            }
            binding.executePendingBindings()
        }
    }

    interface ReviewViewHolderListener {
        fun onReviewSelected(reviewId: Int)
        fun onReviewDeleted(review: Review)
    }
}