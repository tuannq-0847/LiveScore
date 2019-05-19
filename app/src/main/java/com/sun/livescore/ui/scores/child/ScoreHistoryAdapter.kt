package com.sun.livescore.ui.scores.child

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.livescore.R
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.databinding.ItemMatchScoreBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.ui.base.BaseViewHolder
import com.sun.livescore.ui.scores.child.ScoreHistoryAdapter.HistoryViewHolder
import com.sun.livescore.util.Constant
import com.sun.livescore.util.Util

class ScoreHistoryAdapter(histories: List<History>) :
    BaseRecyclerAdapter<ItemMatchScoreBinding, History, HistoryViewHolder>(histories) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemMatchScoreBinding, History> {
        return HistoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), getLayoutRes(viewType), parent, false
            )
        )
    }

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_match_score

    inner class HistoryViewHolder(binding: ItemMatchScoreBinding) :
        BaseViewHolder<ItemMatchScoreBinding, History>(binding) {

        override fun bindView(binding: ItemMatchScoreBinding, position: Int, score: History) {
            binding.run {
                isScore = false
                scoreHistory = score
                val scores = Util.getScoreFromString(score.score)
                scoreHomeTeam = scores[Constant.FIRST_SCORE_INDEX]
                scoreAwayTeam = scores[Constant.SECOND_SCORE_INDEX]
                timeHistory = score.time
            }
        }
    }
}
