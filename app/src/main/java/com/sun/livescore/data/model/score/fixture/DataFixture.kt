package com.sun.livescore.data.model.score.fixture

import com.google.gson.annotations.SerializedName

open class DataFixture(
    @SerializedName(FIXTURES)
    var fixtures: List<Fixture>? = null,
    @SerializedName(NEXT_PAGE)
    var nextPage: String? = null,
    @SerializedName(PREV_PAGE)
    var prevPage: String? = null

) {

    companion object {
        const val FIXTURES = "fixtures"
        const val NEXT_PAGE = "next_page"
        const val PREV_PAGE = "prev_page"
    }
}
