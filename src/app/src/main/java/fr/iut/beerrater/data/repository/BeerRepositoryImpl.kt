package fr.iut.beerrater.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import fr.iut.beerrater.data.data_source.api.BeerApi
import fr.iut.beerrater.data.data_source.api.dto.toBeer
import fr.iut.beerrater.data.data_source.database.BeerDao
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.domain.repository.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BeerRepositoryImpl(
    private val dao: BeerDao,
    private val api: BeerApi
) : BeerRepository {

    override fun getAllBeersWithReviews(): LiveData<List<BeerWithReviews>> {
        /*val beers = dao.getAllBeersWithReviews()
        return liveData {
            beers.ifEmpty {
                val apiBeers = api.getBeers()
                apiBeers.forEach { beer -> dao.insertBeer(beer) }
            }
        }*/
        return dao.getAllBeersWithReviews()
    }

    override fun getAllBeers(): LiveData<List<Beer>> {
        /*Log.i(APP_NAME, "GET ALL BEERS.")
        return withContext(Dispatchers.IO) {
            val beers = async(dao.getAllBeers())
            beers.await()
            Log.i(APP_NAME, "Number of beers get from DB: ${beers.size}")
            if (beers.isNotEmpty()) return@withContext beers
            return liveData<<List<Beer>> {
                val apiBeers = api.getBeers()
                apiBeers.forEach { beer -> dao.insertBeer(beer) }
                apiBeers
            }
        }*/
        /*return liveData {
            val beers = api.getBeers().map {
                it.toBeer()
            }
            beers.map {
                dao.insertBeer(it)
            }
            emit(beers)
        }*/
        return dao.getAllBeers()

    }

    override fun getBeerWithReviewsById(beerId: Int): LiveData<BeerWithReviews?> {
        Log.i(BEER_REPOSITORY_NAME, "GET BEER AND REVIEWS BY BEER ID. BeerId: \"$beerId\".")
        return dao.getBeerWithReviewsById(beerId)
    }

    override fun getReviewById(reviewId: Int): LiveData<Review?> {
        Log.i(BEER_REPOSITORY_NAME, "GET REVIEW BY ID. ReviewId: \"$reviewId\".")
        return dao.getReviewById(reviewId)
    }

    override suspend fun insertReview(review: Review) {
        Log.i(BEER_REPOSITORY_NAME, "INSERT REVIEW. Review: \"$review\" for the beer \"${review.beerId}\".")
        withContext(Dispatchers.IO) {
            dao.insertReview(review)
        }
    }

    override suspend fun updateReview(review: Review) {
        Log.i(BEER_REPOSITORY_NAME, "UPDATE REVIEW. Review: \"$review\".")
        withContext(Dispatchers.IO) {
            dao.updateReview(review)
        }
    }

    override suspend fun deleteReviewById(reviewId: Int) {
        Log.i(BEER_REPOSITORY_NAME, "DELETE REVIEW. Review id: \"$reviewId\".")
        withContext(Dispatchers.IO) {
            dao.deleteReviewById(reviewId)
        }
    }

    override suspend fun deleteAllReviews() {
        Log.i(BEER_REPOSITORY_NAME, "DELETE ALL REVIEWS.")
        withContext(Dispatchers.IO) {
            dao.deleteAllReviews()
        }
    }

    companion object {
        private const val BEER_REPOSITORY_NAME = "BeerRepository"
    }
}