package com.sun.livescore.data.model.league

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    @SerializedName(ID)
    var id: String? = null,
    @SerializedName(NAME)
    var name: String? = null,
    @SerializedName(COUNTRY_ID)
    var countryId: String? = null
) : Parcelable {

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val COUNTRY_ID = "country_id"
    }
}
