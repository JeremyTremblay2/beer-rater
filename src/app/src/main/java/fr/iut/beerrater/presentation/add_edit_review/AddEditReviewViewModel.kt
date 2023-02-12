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
import kotlinx.coroutines.launch

class AddEditReviewViewModel @AssistedInject constructor(
    @Assisted("reviewId") private var reviewId: Int,
    @Assisted("beerId") private var beerId: Int,
    private val addEditReviewUseCase: AddEditReviewUseCase,
    private val getReviewByIdUseCase: GetReviewByIdUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase
) : ViewModel() {
    var review: LiveData<Review?> = if (reviewId != NEW_REVIEW_ID) getReviewByIdUseCase(reviewId) else MutableLiveData(Review())
    private var _isReviewValid: MutableLiveData<Boolean> = MutableLiveData(false)
    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun deleteReview() {
        viewModelScope.launch {
            deleteReviewUseCase(review.value?.reviewId ?: return@launch)
        }
    }

    fun addEditReview() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                addEditReviewUseCase(beerId, review.value ?: return@launch)
                _isReviewValid.postValue(true)
            }
            catch (e: InvalidReviewException) {
                _isReviewValid.postValue(false)
            }
            _isLoading.postValue(false)
        }
    }
}