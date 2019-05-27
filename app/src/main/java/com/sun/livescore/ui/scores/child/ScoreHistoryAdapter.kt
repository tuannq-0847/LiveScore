package com.sun.livescore.ui.scores.child

import android.view.LayoutInflater
import android.view.View
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
import kotlinx.android.synthetic.main.item_match_score.view.textNameLeague

class ScoreHistoryAdapter(
    private var histories: List<History>,
    private val scoreViewModel: ScoreChildViewModel
) :
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

    fun clearData() {
        (histories as ArrayList).clear()
        notifyDataSetChanged()
    }

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_match_score

    inner class HistoryViewHolder(binding: ItemMatchScoreBinding) :
        BaseViewHolder<ItemMatchScoreBinding, History>(binding) {

        override fun bindView(binding: ItemMatchScoreBinding, position: Int, data: History) {
            binding.run {
                isScore = false
                viewModel = scoreViewModel
                scoreHistory = data
                val scores = Util.getScoreFromString(data.score)
                scoreHomeTeam = scores[Constant.FIRST_SCORE_INDEX]
                scoreAwayTeam = scores[Constant.SECOND_SCORE_INDEX]
                timeHistory = data.time
                itemView.textNameLeague.visibility = if (checkSameLeague(position)) View.GONE else View.VISIBLE
            }
        }

        private fun checkSameLeague(position: Int): Boolean =
            (position + 1 < histories.size && histories[position].leagueId == histories[position + 1].leagueId)
    }
}
