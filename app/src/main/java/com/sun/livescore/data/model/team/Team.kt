package com.sun.livescore.data.model.team

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName(NAME)
    var name: String? = null,
    @SerializedName(TEAM_ID)
    var teamId: String? = null,
    @SerializedName(LOGO)
    var logo: String? = null,
    @SerializedName(GLOBAL_TEAM_ID)
    var globalTeamId: String? = null,
    @SerializedName(AREA_ID)
    var areaId: String? = null,
    @SerializedName(VENUE_ID)
    var venueId: String? = null,
    @SerializedName(KEY)
    var key: String? = null
) {

    companion object {
        const val NAME = "Name"
        const val TEAM_ID = "TeamId"
        const val LOGO = "WikipediaLogoUrl"
        const val GLOBAL_TEAM_ID = "GlobalTeamId"
        const val AREA_ID = "AreaId"
        const val VENUE_ID = "VenueId"
        const val KEY = "KEY"
    }
}
