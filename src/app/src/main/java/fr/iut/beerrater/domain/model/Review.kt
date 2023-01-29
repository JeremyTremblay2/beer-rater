package fr.iut.beerrater.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Review(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                  val comment: String = "",
                  val rating: Int = 0,
                  @ColumnInfo(name = "review_date") val reviewDate: Date? = null)