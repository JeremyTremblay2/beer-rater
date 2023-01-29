package fr.iut.beerrater.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Beer_Volume")
data class Volume(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                  @SerializedName("value") @ColumnInfo(name = "volume") val value: Int = 0,
                  @SerializedName("unit") val unit: VolumeUnit
)
{
    enum class VolumeUnit {
        @SerializedName("litres")
        LITER,
        @SerializedName("gallons")
        GALLON
    }
}