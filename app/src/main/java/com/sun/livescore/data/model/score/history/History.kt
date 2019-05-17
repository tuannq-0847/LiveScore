package com.sun.livescore.data.model.score.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sun.livescore.data.model.score.fixture.Fixture
import kotlinx.android.parcel.Parcelize

@Parcelize
data class History(
    @SerializedName(SCORE)
    var score: String? = null,
    @SerializedName(HT_SCORE)
    var htScore: String? = null,
    @SerializedName(FT_SCORE)
    var ftScore: String? = null,
    @SerializedName(STATUS)
    var status: String? = null
) : Parcelable, Fixture() {

    companion object {
        const val SCORE = "score"
        const val HT_SCORE = "ht_score"
        const val FT_SCORE = "ft_score"
        const val STATUS = "status"
    }
}
