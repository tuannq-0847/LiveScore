package com.sun.livescore.data.model.country

import com.google.gson.annotations.SerializedName

data class DataCountry(
    @SerializedName(COUNTRY)
    var countries: List<Country>? = null
) {

    companion object {
        const val COUNTRY = "country"
    }
}
