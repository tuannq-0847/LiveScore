package com.sun.livescore.ui.scores.child

import com.sun.livescore.R
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.databinding.ItemMatchScoreBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.util.Constant.FIRST_SCORE_INDEX

class ScoreFixtureAdapter(fixtures: List<Fixture>) :
    BaseRecyclerAdapter<ItemMatchScoreBinding, Fixture>(fixtures) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_match_score
    }

    override fun bindView(binding: ItemMatchScoreBinding, position: Int, score: Fixture) {
        super.bindView(binding, position, score)
        binding.run {
            isScore = true
            scoreFixture = score
            score.time?.let { timeFixture = cleanUpTimeString(it) }
        }
    }

    private fun cleanUpTimeString(time: String): String {
        return time.substring(FIRST_SCORE_INDEX, time.length - INDEX_THREE)
    }

    companion object {
        const val INDEX_THREE = 3
    }
}
