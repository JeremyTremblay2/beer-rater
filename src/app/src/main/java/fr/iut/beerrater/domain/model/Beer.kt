package fr.iut.beerrater.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Beer(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                @SerializedName("name") val name: String = "Beer",
                @SerializedName("tagline") val tagline: String = "",
                @SerializedName("first_brewed") @ColumnInfo(name = "first_brewed_date") val firstBrewedDate : Date? = null,
                @SerializedName("description") val description: String = "",
                @SerializedName("image_url") @ColumnInfo(name = "image_url") val imageUrl: String? = null,
                @SerializedName("ph") val ph: Float = 7.0f,
                @SerializedName("abv") val abv: Float = 4.5f,
                @SerializedName("volume") @Embedded val volume: Volume? = null,
                @SerializedName("contributed_by") val contributor: String? = null)