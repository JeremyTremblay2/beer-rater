package fr.iut.beerrater.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Beer_Volume")
data class Volume(@PrimaryKey(autoGenerate = true) val volumeId: Int = 0,
                  @ColumnInfo(name = "volume") val value: Int = 0,
                  val unit: VolumeUnit
)
{
    enum class VolumeUnit {
        @SerializedName("litres")
        LITER,
        @SerializedName("gallons")
        GALLON
    }

    override fun toString(): String {
        return String.format("%s%c", value, unit.toString()[0])
    }
}