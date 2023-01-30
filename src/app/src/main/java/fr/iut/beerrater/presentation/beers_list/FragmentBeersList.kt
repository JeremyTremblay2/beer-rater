package fr.iut.beerrater.presentation.beers_list

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.R

@AndroidEntryPoint
class FragmentBeersList : Fragment() {
    private val beerAdapter: BeerRecyclerViewAdapter = BeerRecyclerViewAdapter(ArrayList())
    private lateinit var groupEmptyBar: Group
    private lateinit var viewModel: BeersListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[BeersListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_beers, container, false)
        groupEmptyBar = view.findViewById(R.id.group_empty_bar)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.adapter = beerAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.beers.observe(viewLifecycleOwner) {
            viewModel.beers.value?.let { it1 -> beerAdapter.updateList(it1) }
            groupEmptyBar.visibility = View.GONE
        }
    }
}