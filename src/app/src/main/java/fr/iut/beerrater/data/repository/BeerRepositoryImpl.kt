package fr.iut.beerrater.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.iut.beerrater.common.Constants.APP_NAME
import fr.iut.beerrater.data.data_source.api.BeerApi
import fr.iut.beerrater.data.data_source.database.BeerDao
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.domain.repository.BeerRepository

class BeerRepositoryImpl(
    private val dao: BeerDao,
    private val api: BeerApi
) : BeerRepository {

    override suspend fun getAllBeersWithReviews(): LiveData<List<BeerWithReviews>> {
        /*val beers = dao.getAllBeersWithReviews().value
        beers.let {
            if (it == null || it.isEmpty()) {
                val apiBeers = api.getBeers()
                apiBeers.forEach { beer -> dao.insertBeer(beer) }
                return Result.success(apiBeers);
            }
            return
        }*/
        TODO("Not yet implemented")
    }

    override suspend fun getAllBeers(): LiveData<List<Beer>> {
        val beers = dao.getAllBeers().value
        Log.i(APP_NAME, "Number of beers in the database: $beers.size()")
        beers.let {
            if (it == null || it.isEmpty()) {
                val apiBeers = api.getBeers()
                apiBeers.forEach { beer -> dao.insertBeer(beer) }
                return MutableLiveData(apiBeers)
            }
            return MutableLiveData(beers)
        }
    }

    override suspend fun getBeerWithReviewsById(beerId: Int): BeerWithReviews? {
        val beer = dao.getBeerWithReviewsById(beerId)
        if (beer == null) {
            val apiBeer = api.getBeerById(beerId) ?: return null
            dao.insertBeer(apiBeer)
            return BeerWithReviews(apiBeer, ArrayList())
        }
        return beer
    }

    override suspend fun insertReview(beer: Beer, review: Review) {
        return dao.insertReview(beer, review)
    }

    override suspend fun updateReview(review: Review) {
        return dao.updateReview(review)
    }

    override suspend fun deleteReviewById(reviewId: Int) {
        return dao.deleteReviewById(reviewId)
    }

    override suspend fun deleteAllReviews() {
        return dao.deleteAllReviews()
    }
}