package com.sun.livescore.ui.scores.child

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.databinding.ItemMatchScoreBinding
import com.sun.livescore.ui.scores.child.ScoreAdapter.ViewHolder

class ScoreAdapter(private val score: Any) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMatchScoreBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return when (score) {
            is HistoryResponse
            -> score.data?.histories?.size?.let {
                it
            }!!
            is FixtureResponse
            -> score.data?.fixtures?.size?.let {
                it
            }!!
            else -> 0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(score, position)
    }

    inner class ViewHolder(private val itemBinding: ItemMatchScoreBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(score: Any, position: Int) {
            itemView.run {
                when (score) {
                    is FixtureResponse -> {
                        val fixture = score.data?.fixtures?.get(position)
                        itemBinding.run {
                            fixture?.let { fixture ->
                                isScore = true
                                scoreFixture = fixture
                                timeFixture = fixture.time?.let {
                                    cleanUpTimeString(it)
                                }
                            }
                        }
                    }
                    is HistoryResponse -> {
                        itemBinding.run {
                            val history = score.data?.histories?.get(position)
                            history?.let {
                                scoreHistory = it
                                isScore = false
                                val scores = getScoreFromString(it.score)
                                scoreHomeTeam = scores[START_INDEX]
                                scoreAwayTeam = scores[END_INDEX]
                                timeHistory = it.time
                            }
                        }
                    }

                    else -> {
                    }
                }

            }
        }
    }

    fun cleanUpTimeString(time: String): String {
        return time.substring(START_INDEX, time.length - INDEX_THREE)
    }

    fun getScoreFromString(scores: String?): Array<String> {
        scores?.let {
            val score = it.split(CHAR_SPLIT)
            return arrayOf(score[START_INDEX].trim(), score[END_INDEX].trim())
        }
        return arrayOf()
    }

    companion object {
        const val INDEX_THREE = 3
        const val CHAR_SPLIT = "-"
        const val NAME_LEAGUE_MATCH = 1
        const val RESULT_MATCH = 2
        const val START_INDEX = 0
        const val END_INDEX = 1
    }
}
