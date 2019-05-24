package com.sun.livescore.ui.scores.child

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.livescore.R
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.databinding.ItemMatchScoreBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.ui.base.BaseViewHolder
import com.sun.livescore.ui.scores.child.ScoreFixtureAdapter.FixtureViewHolder

class ScoreFixtureAdapter(
    private val fixtures: List<Fixture>,
    private val scoreViewModel: ScoreChildViewModel
) :
    BaseRecyclerAdapter<ItemMatchScoreBinding, Fixture, FixtureViewHolder>(fixtures) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemMatchScoreBinding, Fixture> {
        return FixtureViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), getLayoutRes(viewType), parent, false
            )
        )
    }

    fun clearData() {
        (fixtures as ArrayList).clear()
        notifyDataSetChanged()
    }

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_match_score

    inner class FixtureViewHolder(binding: ItemMatchScoreBinding) :
        BaseViewHolder<ItemMatchScoreBinding, Fixture>(binding) {

        override fun bindView(binding: ItemMatchScoreBinding, position: Int, data: Fixture) {
            binding.run {
                isScore = true
                viewModel = scoreViewModel
                scoreFixture = data
                data.time?.let { timeFixture = getTimeHourMin(it) }
            }
        }
    }

    private fun getTimeHourMin(time: String): String? {
        return time.substring(FIRST_SCORE_INDEX, time.length - INDEX_THREE)
    }

    companion object {
        const val FIRST_SCORE_INDEX = 0
        const val INDEX_THREE = 3
    }
}
