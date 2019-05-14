package com.sun.livescore.data.model.score.fixture

import com.google.gson.annotations.SerializedName

data class ScoreChildFixture(
    @SerializedName(FIXTURES)
    var scoreFixtures: List<ScoreFixture>,
    @SerializedName(NEXT_PAGE)
    var nextPage: String,
    @SerializedName(PREV_PAGE)
    var prevPage: String
) {

    companion object {
        const val FIXTURES = "fixtures"
        const val NEXT_PAGE = "next_page"
        const val PREV_PAGE = "prev_page"
    }
}
