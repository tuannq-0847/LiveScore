package com.sun.livescore.data.model.score.history

import com.google.gson.annotations.SerializedName
import com.sun.livescore.data.model.score.fixture.DataFixture

data class DataHistory(
    @SerializedName(MATCH)
    var histories: List<History>? = null

) : DataFixture() {

    companion object {
        const val MATCH = "match"
    }
}
