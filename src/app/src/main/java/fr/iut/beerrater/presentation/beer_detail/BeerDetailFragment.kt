package fr.iut.beerrater.presentation.beer_detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.Slider
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.R
import fr.iut.beerrater.common.Constants.DEFAULT_BEER_ID
import fr.iut.beerrater.databinding.FragmentBeerDetailBinding
import fr.iut.beerrater.di.BeerDetailViewModelFactory
import fr.iut.beerrater.di.provideFactory
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.presentation.utils.showSnackBar
import javax.inject.Inject

@AndroidEntryPoint
class BeerDetailFragment : Fragment(), ReviewRecyclerViewAdapter.ReviewViewHolderListener {
    @Inject
    lateinit var beerDetailViewModelFactory: BeerDetailViewModelFactory
    private var listener: OnInteractionListener? = null
    var beerId: Int = 0
    private lateinit var beerDetailViewModel: BeerDetailViewModel
    private val reviewAdapter = ReviewRecyclerViewAdapter(this)

    companion object {
        private const val BEER_ID = "fr.iut.beerrater.presentation.beer_detail.beer_id"

        fun newInstance(beerId: Int) = BeerDetailFragment().apply {
            arguments = bundleOf(BEER_ID to beerId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        binding.beerDetailViewModel = beerDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val seekBar = binding.root.findViewById<AppCompatSeekBar>(R.id.beer_abv_seekbar_value)
        seekBar.isEnabled = false
        with(binding.recyclerViewReviews) {
            adapter = reviewAdapter
        }
        setupListeners(binding)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beerId = savedInstanceState?.getInt(BEER_ID) ?: arguments?.getInt(BEER_ID) ?: DEFAULT_BEER_ID
        this.beerDetailViewModel = ViewModelProvider(this,
            provideFactory(
                beerDetailViewModelFactory,
                beerId
            )
        )[BeerDetailViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.beerDetailViewModel.reviews.observe(viewLifecycleOwner) {
            val beerId = beerId
            reviewAdapter.submitList(it)
        }
        this.beerDetailViewModel.beer.observe(viewLifecycleOwner) {
            val beerId = beerId
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BEER_ID, beerId)
    }

    private fun setupListeners(binding: FragmentBeerDetailBinding) {
        binding.buttonWriteReview.setOnClickListener {
            listener?.onReviewAdded()
        }
    }

    interface OnInteractionListener {
        fun onReviewDeleted(reviewId: Int)
        fun onReviewEdited(reviewId: Int)
        fun onReviewAdded()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("The context $context does not implement OnInteractionListener but it is required.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onReviewSelected(reviewId: Int) {
        listener?.onReviewEdited(reviewId)
    }

    override fun onReviewDeleted(review: Review) {
        beerDetailViewModel.deleteReview(review)
        showSnackBar(requireActivity().findViewById(R.id.parent_layout),
            getString(R.string.review_deleted_text), getString(R.string.undo_text), {
            beerDetailViewModel.restoreDeletedReview()
        })
        listener?.onReviewDeleted(review.reviewId)
    }
}