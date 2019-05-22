package com.sun.livescore.data.model.standing

import com.google.gson.annotations.SerializedName

data class DataStanding(
    @SerializedName(TABLE)
    var tables: List<Standing>
) {

    companion object {
        const val TABLE = "table"
    }
}
