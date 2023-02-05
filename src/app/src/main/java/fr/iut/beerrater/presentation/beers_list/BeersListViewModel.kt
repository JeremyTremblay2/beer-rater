package fr.iut.beerrater.presentation.beers_list

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.use_cases.GetAllBeersUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BeersListViewModel @Inject constructor(
    private val getAllBeersUseCase: GetAllBeersUseCase
    ) : ViewModel() {

    var beers: LiveData<List<Beer>> = getAllBeersUseCase()
    //val groupVisibility = Transformations.map(_beers, List<Beer>::isEmpty)

    init {
        getAllBeers()
    }

    fun getAllBeers() {

    }
}