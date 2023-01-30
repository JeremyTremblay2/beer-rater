package fr.iut.beerrater.data.data_source.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.model.Review

@Dao
interface BeerDao {
    @Transaction
    @Query("SELECT * FROM Beer ORDER BY first_brewed_date ASC")
    fun getAllBeersWithReviews(): LiveData<List<BeerWithReviews>>

    @Query("SELECT * FROM Beer ORDER BY first_brewed_date ASC")
    fun getAllBeers(): LiveData<List<Beer>>

    @Transaction
    @Query("SELECT * FROM Beer WHERE id = :beerId")
    suspend fun getBeerWithReviewsById(beerId: Int): BeerWithReviews?

    @Insert(onConflict = REPLACE)
    suspend fun insertBeer(vararg beer: Beer)

    @Insert(onConflict = REPLACE)
    suspend fun insertReview(beer: Beer, review: Review)

    @Update(onConflict = REPLACE)
    suspend fun updateReview(review: Review)

    @Delete()
    suspend fun deleteReviewById(reviewId: Int)

    @Query("DELETE FROM Review")
    suspend fun deleteAllReviews()
}