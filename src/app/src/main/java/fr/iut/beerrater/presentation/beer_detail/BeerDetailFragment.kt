package fr.iut.beerrater.presentation.beer_detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.android.material.slider.Slider
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.R
import viewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class FragmentBeerDetail : Fragment() {
    private var listener: OnInteractionListener? = null
    private var beerId: Int = 0
    @Inject
    lateinit var beerDetailViewModelFactory: BeerDetailViewModelFactory
    private lateinit var beerDetailViewModel: BeerDetailViewModel
    private lateinit var beerName: TextView
    private lateinit var slider: Slider

    companion object {
        private const val BEER_ID = "fr.iut.beerrater.presentation.beer_detail.beer_id"

        fun newInstance(beerId: Int) = FragmentBeerDetail().apply {
            arguments = bundleOf(BEER_ID to beerId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beerId = savedInstanceState?.getInt(BEER_ID) ?: arguments?.getInt(BEER_ID) ?: 0
        val beerDetailViewModel by viewModels<BeerDetailViewModel> {
            provideFactory(
                beerDetailViewModelFactory,
                beerId
            )
        }
        this.beerDetailViewModel = beerDetailViewModel


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BEER_ID, beerId)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*val binding = FragmentDogBinding.inflate(inflater)
        binding.dogVM = dogVM
        binding.lifecycleOwner = viewLifecycleOwner
        }*/
        val view =  inflater.inflate(R.layout.fragment_beer_detail, container, false);
        beerName = view.findViewById(R.id.beer_name_detail)
        slider = view.findViewById(R.id.beer_ph_slider_value)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.beerDetailViewModel.beer.observe(viewLifecycleOwner) {
            if (it != null) {
                beerName.text = it.beer.name
                slider.value = it.beer.ph
            }
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
}