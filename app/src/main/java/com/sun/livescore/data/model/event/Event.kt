package com.sun.livescore.data.model.event

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName(ID)
    var eventId: String? = null,
    @SerializedName(MATCH_ID)
    var matchId: String? = null,
    @SerializedName(PLAYER)
    var player: String? = null,
    @SerializedName(EVENT)
    var event: String? = null,
    @SerializedName(HOME_AWAY)
    var home_away: String? = null,
    @SerializedName(TIME)
    var time: String? = null
) {

    companion object {
        const val ID = "id"
        const val MATCH_ID = "match_id"
        const val PLAYER = "player"
        const val EVENT = "event"
        const val HOME_AWAY = "home_away"
        const val TIME = "time"
    }
}
