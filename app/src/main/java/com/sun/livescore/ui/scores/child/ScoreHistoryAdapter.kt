package com.sun.livescore.ui.scores.child

import com.sun.livescore.R
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.databinding.ItemMatchScoreBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.util.Constant
import com.sun.livescore.util.Util

class ScoreHistoryAdapter(histories: List<History>) :
    BaseRecyclerAdapter<ItemMatchScoreBinding, History>(histories) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_match_score

    override fun bindView(binding: ItemMatchScoreBinding, position: Int, score: History) {
        super.bindView(binding, position, score)
        binding.run {
            isScore = false
            scoreHistory = score
            val scores = Util.getScoreFromString(score.score)
            scoreHomeTeam = scores[Constant.FIRST_SCORE_INDEX]
            scoreAwayTeam = scores[Constant.SECOND_SCORE_INDEX]
            timeHistory = score.time
        }
    }

    companion object {
        const val NAME_LEAGUE_MATCH = 1
        const val RESULT_MATCH = 2
    }
}
