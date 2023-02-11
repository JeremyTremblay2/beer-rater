package fr.iut.beerrater.presentation.beer_detail

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.beerrater.common.Constants.DEFAULT_BEER_ID
import fr.iut.beerrater.domain.model.Beer
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.domain.use_cases.AddEditReviewUseCase
import fr.iut.beerrater.domain.use_cases.DeleteReviewUseCase
import fr.iut.beerrater.domain.use_cases.GetBeerByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class BeerDetailViewModel @AssistedInject constructor(
    @Assisted private var beerId: Int,
    private val getBeerByIdUseCase: GetBeerByIdUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase,
    private val addReviewUseCase: AddEditReviewUseCase
    ): ViewModel() {

    var beer: LiveData<BeerWithReviews?> = getBeerByIdUseCase(beerId)
    val groupVisibility = Transformations.map(beer) { item ->
        item?.reviews?.isEmpty() ?: false
    }
    private var recentlyDeletedReview: Review? = null

    init {
        beer = getBeerByIdUseCase(beerId)
    }

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