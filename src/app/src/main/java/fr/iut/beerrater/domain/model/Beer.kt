package fr.iut.beerrater.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Beer(@PrimaryKey(autoGenerate = true) val beerId: Int = 0,
                val name: String = "Beer",
                val tagline: String = "",
                @ColumnInfo(name = "first_brewed_date") val firstBrewedDate : Date? = null,
                val description: String = "",
                @ColumnInfo(name = "image_url") val imageUrl: String? = null,
                val ph: Float = 7.0f,
                val abv: Float = 4.5f,
                @Embedded val volume: Volume? = null,
                val contributor: String? = null)