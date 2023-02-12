package fr.iut.beerrater.domain.repository

import androidx.lifecycle.LiveData
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.domain.model.Beer

interface BeerRepository {
    fun getAllBeersWithReviews(): LiveData<List<BeerWithReviews>>

    fun getAllBeers(): LiveData<List<Beer>>

    fun getBeerWithReviewsById(beerId: Int): LiveData<BeerWithReviews?>

    fun getReviewById(reviewId: Int): LiveData<Review?>

    suspend fun insertReview(review: Review)

    suspend fun updateReview(review: Review)

    suspend fun deleteReviewById(reviewId: Int)

    suspend fun deleteAllReviews()
}