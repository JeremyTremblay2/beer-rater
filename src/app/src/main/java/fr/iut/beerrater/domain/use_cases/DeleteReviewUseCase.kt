package fr.iut.beerrater.domain.use_cases

import androidx.lifecycle.LiveData
import fr.iut.beerrater.common.Constants.NEW_REVIEW_ID
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.repository.BeerRepository

class DeleteReviewUseCase(
    private val repository: BeerRepository
) {
    suspend operator fun invoke(reviewId: Int) {
        if (reviewId != NEW_REVIEW_ID) repository.deleteReviewById(reviewId)
    }
}