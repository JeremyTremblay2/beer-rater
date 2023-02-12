package fr.iut.beerrater.presentation.beer_list

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import fr.iut.beerrater.common.Constants.APP_NAME
import fr.iut.beerrater.domain.repository.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*class GetBeersFromAPIWorker(appContext: Context, workerParameters: WorkerParameters)
    : Worker(appContext, workerParameters) {

    @Inject lateinit var repository: BeerRepository

    override suspend fun doWork(): Result {
        Log.i(APP_NAME, "GET Beers from API to fill in the database with worker.")
        withContext(Dispatchers.IO) {
            repository.getAllBeersFromCache().forEach {
                repository.insertBeer(it)
            }
        }

        return Result.success()
    }
}*/