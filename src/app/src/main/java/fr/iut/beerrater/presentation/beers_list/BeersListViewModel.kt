package fr.iut.beerrater.presentation.beers_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.use_cases.GetAllBeersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeersListViewModel @Inject constructor(
    private val getAllBeersUseCase: GetAllBeersUseCase
    ) : ViewModel() {

    private val _beers: MutableLiveData<List<Beer>> = MutableLiveData()

    val beers: LiveData<List<Beer>>
            get() = _beers

    init {
        getAllBeers()
    }

    fun getAllBeers() {
        viewModelScope.launch {
            _beers.postValue(getAllBeersUseCase.invoke().value)
        }
    }
}