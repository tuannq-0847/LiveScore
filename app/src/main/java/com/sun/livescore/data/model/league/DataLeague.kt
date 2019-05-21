package com.sun.livescore.data.model.league

import com.google.gson.annotations.SerializedName

data class DataLeague(
    @SerializedName("league")
    var leagues: List<League>? = null
)
