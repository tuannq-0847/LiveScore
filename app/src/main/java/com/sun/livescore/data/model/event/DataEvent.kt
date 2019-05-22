package com.sun.livescore.data.model.event

import com.google.gson.annotations.SerializedName

data class DataEvent(
    @SerializedName(EVENT)
    var events: List<Event>
) {

    companion object {
        const val EVENT = "event"
    }
}
