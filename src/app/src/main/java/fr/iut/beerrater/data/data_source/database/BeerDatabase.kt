package fr.iut.beerrater.data.data_source.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.iut.beerrater.data.data_source.database.converters.DateToLongConverter
import fr.iut.beerrater.domain.model.BeerWithReviews
import fr.iut.beerrater.domain.model.Review
import fr.iut.beerrater.domain.model.Volume
import fr.iut.beerrater.data.data_source.database.converters.VolumeUnitToIntConverter
import fr.iut.beerrater.domain.model.Beer

@Database(
    entities = [Beer::class, Review:: class, Volume::class],
    version = 1
)
@TypeConverters(VolumeUnitToIntConverter::class, DateToLongConverter::class)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDAO(): BeerDao

    companion object {
        const val BEER_DB_FILENAME = "beers.db"
    }
}
