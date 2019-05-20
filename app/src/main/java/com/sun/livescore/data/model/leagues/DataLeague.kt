package com.sun.livescore.data.model.leagues

import com.google.gson.annotations.SerializedName

data class DataLeague(
    @SerializedName(COUNTRY)
    var leagueCountries: List<LeagueCountry>? = null
) {

    companion object {
        const val COUNTRY = "country"
    }
}
