package fr.iut.beerrater.domain.use_cases

import androidx.lifecycle.LiveData
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.repository.BeerRepository

class GetAllBeersUseCase(
    private val repository: BeerRepository
) {
    operator fun invoke(): LiveData<List<Beer>> {
        return repository.getAllBeers()
    }
}