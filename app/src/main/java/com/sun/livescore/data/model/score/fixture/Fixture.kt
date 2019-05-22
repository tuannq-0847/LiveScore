package com.sun.livescore.data.model.score.fixture

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Fixture(
    @SerializedName(ID)
    var id: String? = null,
    @SerializedName(DATE)
    var date: String? = null,
    @SerializedName(HOME_NAME)
    var homeTeamName: String? = null,
    @SerializedName(AWAY_NAME)
    var awayTeamName: String? = null,
    @SerializedName(TIME)
    var time: String? = null,
    @SerializedName(LOCATION)
    var location: String? = null,
    @SerializedName(LEAGUE_ID)
    var leagueId: String? = null
) : Parcelable {

    companion object {
        const val DATE = "date"
        const val HOME_NAME = "home_name"
        const val AWAY_NAME = "away_name"
        const val TIME = "time"
        const val ID = "id"
        const val LOCATION = "location"
        const val LEAGUE_ID = "league_id"
    }
}
