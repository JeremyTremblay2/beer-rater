package fr.iut.beerrater.presentation.add_edit_review

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import fr.iut.beerrater.common.Constants.NEW_REVIEW_ID
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.model.InvalidReviewException
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.domain.use_cases.AddEditReviewUseCase
import fr.iut.beerrater.domain.use_cases.DeleteReviewUseCase
import fr.iut.beerrater.domain.use_cases.GetReviewByIdUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddEditReviewViewModel @AssistedInject constructor(
    @Assisted("reviewId") private var reviewId: Int,
    @Assisted("beerId") private var beerId: Int,
    private val addEditReviewUseCase: AddEditReviewUseCase,
    private val getReviewByIdUseCase: GetReviewByIdUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase
) : ViewModel() {

    private val _reviewLoadingStatus: MutableLiveData<ReviewLoadingStatus?> = MutableLiveData()
    val reviewLoadingStatus: LiveData<ReviewLoadingStatus?>
        get() = _reviewLoadingStatus

    val review: LiveData<Review?> = if (reviewId != NEW_REVIEW_ID) getReviewByIdUseCase(reviewId) else MutableLiveData(Review())

    fun deleteReview() {
        viewModelScope.launch {
            deleteReviewUseCase(review.value?.reviewId ?: return@launch)
        }
    }

    fun addEditReview() {
        viewModelScope.launch {
            _reviewLoadingStatus.postValue(ReviewLoadingStatus.LOADING)
            // Used to simulate a long treatment, it is possible to remove it but the progress bar
            // will not be visible because the program is too fast to allows us to see it.
            delay(1000)
            try {
                addEditReviewUseCase(beerId, review.value ?: return@launch)
                _reviewLoadingStatus.postValue(ReviewLoadingStatus.DONE)
            }
            catch (e: InvalidReviewException) {
                _reviewLoadingStatus.postValue(ReviewLoadingStatus.ERROR)
            }
        }
    }
}