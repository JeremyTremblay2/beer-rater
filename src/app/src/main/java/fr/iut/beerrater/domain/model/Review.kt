package fr.iut.beerrater.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Review(@PrimaryKey(autoGenerate = true) val reviewId: Int = 0,
                  val title: String = "",
                  val comment: String = "",
                  val rating: Int = 0,
                  @ColumnInfo(name = "review_date") var reviewDate: Date? = null)

class InvalidReviewException(message: String) : Exception(message)