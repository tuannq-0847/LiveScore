package com.sun.livescore.data.model.score.base

import com.google.gson.annotations.SerializedName

open class BaseResponse<T>(
    @SerializedName(SUCCESS)
    var success: String? = null,
    @SerializedName(DATA)
    var data: T? = null
) {

    companion object {
        const val SUCCESS = "success"
        const val DATA = "data"
    }
}
