package com.sun.livescore.data.model.score.history

import com.google.gson.annotations.SerializedName

data class ScoreChildHistory(
    @SerializedName(MATCH)
    var scoreHistories: List<ScoreHistory>,
    @SerializedName(NEXT_PAGE)
    var nextPage: String,
    @SerializedName(PREV_PAGE)
    var prevPage: String
) {

    companion object {
        const val MATCH = "match"
        const val NEXT_PAGE = "next_page"
        const val PREV_PAGE = "prev_page"
    }
}
