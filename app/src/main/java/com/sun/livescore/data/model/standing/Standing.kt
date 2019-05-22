package com.sun.livescore.data.model.standing

import com.google.gson.annotations.SerializedName

data class Standing(
    @SerializedName(LEAGUE_ID)
    var leagueId: String? = null,
    @SerializedName(SEASON_ID)
    var seasonId: String? = null,
    @SerializedName(NAME)
    var name: String? = null,
    @SerializedName(RANK)
    var rank: String? = null,
    @SerializedName(POINTS)
    var points: String? = null,
    @SerializedName(MATCHES)
    var matches: String? = null,
    @SerializedName(GOAL_DIFF)
    var goalDiff: String? = null,
    @SerializedName(GOALS_SCORED)
    var goalScored: String? = null,
    @SerializedName(GOALS_CONCEDED)
    var goalConceded: String? = null,
    @SerializedName(LOST)
    var lost: String? = null,
    @SerializedName(TEAM_ID)
    var teamId: String? = null
) {

    companion object {
        const val LEAGUE_ID = "league_id"
        const val SEASON_ID = "season_id"
        const val NAME = "name"
        const val RANK = "rank"
        const val POINTS = "points"
        const val MATCHES = "matches"
        const val GOAL_DIFF = "goal_diff"
        const val GOALS_SCORED = "goals_scored"
        const val GOALS_CONCEDED = "goals_conceded"
        const val LOST = "lost"
        const val DRAWN = "drawn"
        const val WON = "won"
        const val TEAM_ID = "team_id"
    }
}
