package com.sun.livescore.ui.scores.live

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
import kotlinx.android.synthetic.main.item_match_score.view.textHomeName
import kotlinx.android.synthetic.main.item_match_score.view.textNameLeague
import kotlinx.android.synthetic.main.item_match_score.view.viewOnline

class LiveScoreAdapter(
    private val histories: List<History>,
    private val onMatchClick: (history: History) -> Unit
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

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_match_score

    inner class HistoryViewHolder(binding: ItemMatchScoreBinding) :
        BaseViewHolder<ItemMatchScoreBinding, History>(binding) {

        override fun bindView(binding: ItemMatchScoreBinding, position: Int, data: History) {
            binding.run {
                isScore = false
                itemView.viewOnline.visibility = View.VISIBLE
                showValidTime(binding, data)
                scoreHistory = data
                val scores = Util.getScoreFromString(data.score)
                scoreHomeTeam = scores[Constant.FIRST_SCORE_INDEX]
                scoreAwayTeam = scores[Constant.SECOND_SCORE_INDEX]
                itemView.textNameLeague.visibility = if (checkSameLeague(position)) View.GONE else View.VISIBLE
                itemView.textHomeName.setOnClickListener {
                    onMatchClick(data)
                }
            }
        }

        private fun checkSameLeague(position: Int): Boolean =
            (position + 1 < histories.size && histories[position].leagueName == histories[position + 1].leagueName)
    }

    private fun showValidTime(binding: ItemMatchScoreBinding, data: History) {
        binding.timeHistory = if (data.time != HALF_TIME) {
            "${data.time}'"
        } else {
            data.time
        }
    }

    companion object {
        const val HALF_TIME = "HT"
    }
}
