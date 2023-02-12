package fr.iut.beerrater.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class BeerWithReviews(@Embedded val beer: Beer,
                           @Relation(parentColumn = "beerId", entityColumn = "reviewId", entity = Review::class)
                           val reviews: List<Review>)