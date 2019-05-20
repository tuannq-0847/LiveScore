package com.sun.livescore.data.remote.response

import com.sun.livescore.data.model.EnumStatus
import com.sun.livescore.data.model.EnumStatus.ERROR
import com.sun.livescore.data.model.EnumStatus.LOADING
import com.sun.livescore.data.model.EnumStatus.SUCCESS

class ApiResponse<T>(
    val status: EnumStatus,
    val data: T?,
    val message: String
) {

    companion object {

        fun <T> loading() = ApiResponse<T>(LOADING, null,"")

        fun <T> success(data: T?) = ApiResponse(SUCCESS, data, "")

        fun <T> error(msg: String) = ApiResponse<T>(ERROR, null, msg)
    }
}
