package fr.iut.beerrater.di

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.iut.beerrater.common.Constants
import fr.iut.beerrater.data.data_source.api.BeerApi
import fr.iut.beerrater.data.data_source.database.BeerDatabase
import fr.iut.beerrater.data.repository.BeerRepositoryImpl
import fr.iut.beerrater.domain.repository.BeerRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BeerModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(app: Application): BeerDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            BeerDatabase::class.java,
            BeerDatabase.BEER_DB_FILENAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi(): BeerApi {
        return Retrofit.Builder()
            .baseUrl(Constants.FULL_API_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setDateFormat("MM/yyyy")
                        .create()
                )
            )
            .build()
            .create(BeerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBeerRepository(database: BeerDatabase, api: BeerApi): BeerRepository {
        return BeerRepositoryImpl(database.beerDAO(), api)
    }
}