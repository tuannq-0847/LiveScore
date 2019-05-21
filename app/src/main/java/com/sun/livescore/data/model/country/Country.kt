package com.sun.livescore.data.model.country

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    @SerializedName(ID)
    var id: String? = null,
    @SerializedName(NAME)
    var nameLeague: String? = null,
    @SerializedName(IS_REAL)
    var isRealLeague: String? = null,
    @SerializedName(LEAGUES)
    var leagueLink: String? = null
) : Parcelable {

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val IS_REAL = "is_real"
        const val LEAGUES = "leagues"
    }
}
