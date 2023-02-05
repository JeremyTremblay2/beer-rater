package fr.iut.beerrater.presentation.beer_detail

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.iut.beerrater.R
import viewModelFactory

class FragmentBeerDetail : Fragment() {
    companion object {
        private const val BEER_ID = "fr.iut.beerrater.presentation.beer_detail.beer_id"

        fun newInstance(beerId: Int) = FragmentBeerDetail().apply {
            arguments = bundleOf(BEER_ID to beerId)
        }
    }

    private var beerId: Int = 0
    private lateinit var beerViewModel: BeerDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beerId = savedInstanceState?.getInt(BEER_ID) ?: arguments?.getInt(BEER_ID) ?: 0

        beerViewModel = ViewModelProvider(this, viewModelFactory { BeerDetailViewModel(beerId) }).get()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(EXTRA_DOG_ID, dogId)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDogBinding.inflate(inflater)
        binding.dogVM = dogVM
        binding.lifecycleOwner = viewLifecycleOwner
        binding.textDogOwner.setOnClickListener {
            pickOwner.launch()
        }

        binding.textDogAdmissionDate.setOnClickListener {
            val dateDialog =
                DatePickerFragment.newInstance(REQUEST_DATE, dogVM.admissionDateLiveData.value)
            dateDialog.show(parentFragmentManager, DIALOG_DATE)
        }

        return binding.root
    }
}