package fr.iut.beerrater.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory
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
