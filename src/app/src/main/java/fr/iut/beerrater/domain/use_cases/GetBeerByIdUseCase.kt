package fr.iut.beerrater.domain.use_cases

import androidx.lifecycle.LiveData
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.repository.BeerRepository

class GetBeerByIdUseCase(
    private val repository: BeerRepository
) {
    suspend operator fun invoke(beerId: Int): LiveData<BeerWithReviews?> {
        return repository.getBeerWithReviewsById(beerId)
    }
}