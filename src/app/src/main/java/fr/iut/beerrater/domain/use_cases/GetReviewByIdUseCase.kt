package fr.iut.beerrater.domain.use_cases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import fr.iut.beerrater.common.Constants
import fr.iut.beerrater.common.Constants.NEW_REVIEW_ID
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.domain.repository.BeerRepository

class GetReviewByIdUseCase(
    private val repository: BeerRepository
) {
    suspend operator fun invoke(reviewId: Int): LiveData<Review?> {
        return if (reviewId == NEW_REVIEW_ID) MutableLiveData(Review())
        else repository.getReviewById(reviewId)
    }
}