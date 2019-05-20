package com.sun.livescore.data.model.leagues

import com.google.gson.annotations.SerializedName

data class LeagueCountry(
    @SerializedName(ID)
    var id: String? = null,
    @SerializedName(NAME)
    var nameLeague: String? = null,
    @SerializedName(IS_REAL)
    var isRealLeague: String? = null
) {

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val IS_REAL = "is_real"
    }
}
