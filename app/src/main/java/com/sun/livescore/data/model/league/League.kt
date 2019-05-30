package com.sun.livescore.data.model.league

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sun.livescore.data.model.league.League.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Entity(tableName = TABLE_NAME)
@Parcelize
data class League(
    @PrimaryKey
    @SerializedName(ID)
    var id: String,
    @SerializedName(NAME)
    var name: String? = null,
    @SerializedName(COUNTRY_ID)
    var countryId: String? = null
) : Parcelable {

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val COUNTRY_ID = "country_id"
        const val TABLE_NAME = "league"
    }
}
