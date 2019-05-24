package com.sun.livescore.util

import android.view.View
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.data.model.score.history.History
import kotlinx.android.synthetic.main.item_match_score.view.textNameLeague

object Util {
    fun getScoreFromString(scores: String?): Array<String> {
        scores?.let {
            val score = it.split(Constant.SCORE_REGEX)
            return arrayOf(score[Constant.FIRST_SCORE_INDEX].trim(), score[Constant.SECOND_SCORE_INDEX].trim())
        }
        return arrayOf()
    }

    fun checkSameLeague(histories: List<History>, position: Int, itemView: View) {
        when {
            position + 1 < histories.size && (histories[position].leagueName == histories[position + 1].leagueName)
            -> itemView.textNameLeague.visibility = View.GONE
        }
    }

    fun checkSameFixtureLeague(fixtures: List<Fixture>, position: Int, itemView: View) {
        when {
            position + 1 < fixtures.size && (fixtures[position].leagueName == fixtures[position + 1].leagueName)
            -> itemView.textNameLeague.visibility = View.GONE
        }
    }
}
