package fr.iut.beerrater.domain.use_cases

import fr.iut.beerrater.common.Constants.NEW_REVIEW_ID
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.model.InvalidReviewException
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.domain.repository.BeerRepository
import java.util.*

class AddEditReviewUseCase(
    private val repository: BeerRepository
) {
    suspend operator fun invoke(beerId: Int, review: Review, isNew: Boolean = false) {
        if (review.title.isBlank()) {
            throw InvalidReviewException("The title of the review cannot be empty")
        }
        if (review.rating < 0 || review.rating > 5) {
            throw InvalidReviewException("The review note must be between 1 and 5.")
        }
        if (review.comment.isBlank()) {
            throw InvalidReviewException("The comment of the review cannot be empty")
        }
        review.reviewDate = Date(System.currentTimeMillis())
        review.beerId = beerId
        if (review.reviewId == NEW_REVIEW_ID || isNew) repository.insertReview(review)
        else repository.updateReview(review)
    }
}