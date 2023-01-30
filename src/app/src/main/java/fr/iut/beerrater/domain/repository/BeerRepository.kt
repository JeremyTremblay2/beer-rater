package fr.iut.beerrater.domain.repository

import androidx.lifecycle.LiveData
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.domain.model.Beer

interface BeerRepository {
    suspend fun getAllBeersWithReviews(): LiveData<List<BeerWithReviews>>

    suspend fun getAllBeers(): LiveData<List<Beer>>

    suspend fun getBeerWithReviewsById(beerId: Int): BeerWithReviews?

    suspend fun insertReview(beer: Beer, review: Review)

    suspend fun updateReview(review: Review)

    suspend fun deleteReviewById(reviewId: Int)

    suspend fun deleteAllReviews()
}