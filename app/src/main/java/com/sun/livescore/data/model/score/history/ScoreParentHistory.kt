package com.sun.livescore.data.model.score.history

import com.google.gson.annotations.SerializedName
import com.sun.livescore.data.model.ScoreParent

data class ScoreParentHistory(
    @SerializedName(DATA)
    var data: ScoreChildHistory? = null
) : ScoreParent() {

    companion object {
        const val DATA = "data"
    }
}
