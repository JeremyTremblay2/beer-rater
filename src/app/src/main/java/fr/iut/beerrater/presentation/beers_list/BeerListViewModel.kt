package fr.iut.beerrater.presentation.beers_list

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.use_cases.GetAllBeersUseCase
import javax.inject.Inject


@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val getAllBeersUseCase: GetAllBeersUseCase
    ) : ViewModel() {

    //private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    //val isLoading: LiveData<Boolean>
    //    get() = _isLoading

    val beers: LiveData<List<Beer>> = getAllBeersUseCase()

    val groupVisibility = Transformations.map(beers, List<Beer>::isEmpty)

    init {
        //_isLoading.postValue(true)
        // beers = getAllBeersUseCase()
        //_isLoading.postValue(false)
    }
}