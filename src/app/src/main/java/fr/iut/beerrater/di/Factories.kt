package fr.iut.beerrater.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import fr.iut.beerrater.presentation.add_edit_review.AddEditReviewViewModel
import fr.iut.beerrater.presentation.beer_detail.BeerDetailViewModel

@AssistedFactory
interface BeerDetailViewModelFactory {
    fun create(
        beerId: Int
    ): BeerDetailViewModel
}

fun provideFactory(
    assistedFactory: BeerDetailViewModelFactory,
    beerId: Int
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(beerId) as T
    }
}

@AssistedFactory
interface AddEditReviewViewModelFactory {
    fun create(
        @Assisted("reviewId") reviewId: Int,
        @Assisted("beerId") beerId: Int
    ): AddEditReviewViewModel
}

fun provideFactory(
    assistedFactory: AddEditReviewViewModelFactory,
    reviewId: Int,
    beerId: Int
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(reviewId, beerId) as T
    }
}
