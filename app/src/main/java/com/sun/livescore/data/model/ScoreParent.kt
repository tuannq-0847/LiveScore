package com.sun.livescore.data.model

import com.google.gson.annotations.SerializedName

open class ScoreParent(
    @SerializedName(SUCCESS)
    var success: String? = null
) {

    companion object {
        const val SUCCESS = "success"
    }
}
