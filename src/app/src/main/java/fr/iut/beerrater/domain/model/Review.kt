package fr.iut.beerrater.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import fr.iut.beerrater.common.Constants.DEFAULT_BEER_ID
import fr.iut.beerrater.common.Constants.NEW_REVIEW_ID
import java.util.*

@Entity
data class Review(@PrimaryKey(autoGenerate = true) var reviewId: Int = NEW_REVIEW_ID,
                  var beerId: Int = DEFAULT_BEER_ID,
                  var title: String = "",
                  var comment: String = "",
                  var rating: Int = 0,
                  @ColumnInfo(name = "review_date") var reviewDate: Date? = null)

class InvalidReviewException(message: String) : Exception(message)