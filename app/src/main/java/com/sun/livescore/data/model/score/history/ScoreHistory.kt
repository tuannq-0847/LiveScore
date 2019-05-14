package com.sun.livescore.data.model.score.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sun.livescore.data.model.score.fixture.ScoreFixture
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScoreHistory(
    @SerializedName(SCORE)
    var score: String,
    @SerializedName(HT_SCORE)
    var htScore: String,
    @SerializedName(FT_SCORE)
    var ftScore: String,
    @SerializedName(STATUS)
    var status: String
) : ScoreFixture(), Parcelable {

    companion object {
        const val SCORE = "score"
        const val HT_SCORE = "ht_score"
        const val FT_SCORE = "ft_score"
        const val STATUS = "status"
    }
}
