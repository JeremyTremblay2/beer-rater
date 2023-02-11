package fr.iut.beerrater.domain.use_cases

import androidx.lifecycle.LiveData
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.repository.BeerRepository
import kotlinx.coroutines.delay

class GetBeerByIdUseCase(
    private val repository: BeerRepository
) {
    operator fun invoke(beerId: Int): LiveData<BeerWithReviews?> {
        return repository.getBeerWithReviewsById(beerId)
    }
}