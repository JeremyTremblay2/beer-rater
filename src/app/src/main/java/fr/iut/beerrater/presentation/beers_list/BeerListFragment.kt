package fr.iut.beerrater.presentation.beers_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.databinding.FragmentBeerListBinding

@AndroidEntryPoint
class BeerListFragment : Fragment(), BeerRecyclerViewAdapter.BeerViewHolderListener {
    private var listener: OnInteractionListener? = null
    private val beerAdapter: BeerRecyclerViewAdapter = BeerRecyclerViewAdapter(this)
    private lateinit var viewModel: BeerListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[BeerListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBeerListBinding.inflate(inflater, container, false)
        binding.beerListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        with(binding.recyclerView) {
            adapter = beerAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.beers.observe(viewLifecycleOwner) {
            beerAdapter.submitList(it)
        }
    }

    interface OnInteractionListener {
        fun onBeerSelected(beerId: Int)
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

    override fun onBeerSelected(beerId: Int) {
        listener?.onBeerSelected(beerId)
    }
}