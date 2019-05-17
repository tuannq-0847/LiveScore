package com.sun.livescore.ui.scores.child

import com.sun.livescore.R
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.databinding.ItemMatchScoreBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.util.Constant
import com.sun.livescore.util.Util

class ScoreHistoryAdapter(private val historyResponse: HistoryResponse) :
    BaseRecyclerAdapter<ItemMatchScoreBinding>(historyResponse) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_match_score

    override fun bindViewHistory(binding: ItemMatchScoreBinding, histories: List<History>, position: Int) {
        super.bindViewHistory(binding, histories, position)
        binding.run {
            isScore = false
            scoreHistory = histories[position]
            val scores = Util.getScoreFromString(histories[position].score)
            scoreHomeTeam = scores[Constant.FIRST_SCORE_INDEX]
            scoreAwayTeam = scores[Constant.SECOND_SCORE_INDEX]
            timeHistory = histories[position].time
        }
    }

    companion object {
        const val NAME_LEAGUE_MATCH = 1
        const val RESULT_MATCH = 2
    }
}
