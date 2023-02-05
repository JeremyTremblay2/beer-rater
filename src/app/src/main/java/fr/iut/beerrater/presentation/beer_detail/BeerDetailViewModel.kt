package fr.iut.beerrater.presentation.beer_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.domain.use_cases.AddEditReviewUseCase
import fr.iut.beerrater.domain.use_cases.DeleteReviewUseCase
import fr.iut.beerrater.domain.use_cases.GetBeerByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class BeerDetailViewModel(

    private val beerId: Int
    ): ViewModel() {
    @Inject private lateinit var getBeerByIdUseCase: GetBeerByIdUseCase
    @Inject private lateinit var deleteReviewUseCase: DeleteReviewUseCase
    @Inject private lateinit var addReviewUseCase: AddEditReviewUseCase
    private var recentlyDeletedReview: Review? = null
    val beer: LiveData<BeerWithReviews> = getBeerByIdUseCase(beerId)

    fun deleteReview(review: Review) {
        viewModelScope.launch {
            deleteReviewUseCase(review.reviewId)
            recentlyDeletedReview = review
        }
    }

    fun restoreDeletedReview() {
        viewModelScope.launch {
            addReviewUseCase(beer.value?.beer ?: return@launch,
                recentlyDeletedReview ?: return@launch)
            recentlyDeletedReview = null
        }
    }

}