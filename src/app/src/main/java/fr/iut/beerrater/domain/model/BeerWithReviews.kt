package fr.iut.beerrater.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class BeerWithReviews(@Embedded val beer: Beer,
                           @Relation(parentColumn = "id", entityColumn = "reviewId")
                           val reviews: List<Review>)