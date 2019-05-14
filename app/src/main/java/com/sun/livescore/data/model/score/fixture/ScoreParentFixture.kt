package com.sun.livescore.data.model.score.fixture

import com.google.gson.annotations.SerializedName
import com.sun.livescore.data.model.ScoreParent

data class ScoreParentFixture(
    @SerializedName(DATA)
    var data: ScoreChildFixture? = null
) : ScoreParent() {

    companion object {
        const val DATA = "data"
    }
}
