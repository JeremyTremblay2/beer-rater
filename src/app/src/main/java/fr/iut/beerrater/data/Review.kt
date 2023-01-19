package fr.iut.beerrater.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Review(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                  val comment: String = "",
                  val rating: Int = 0,
                  val reviewDate: LocalDate = LocalDate.now())