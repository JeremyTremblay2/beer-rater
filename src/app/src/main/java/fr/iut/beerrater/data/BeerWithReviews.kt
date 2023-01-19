package fr.iut.beerrater.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class BeerWithReviews(@Embedded val beer: Beer,
                           @Relation(parentColumn = "id", entityColumn = "reviewId")
                           val reviews: List<Review>)