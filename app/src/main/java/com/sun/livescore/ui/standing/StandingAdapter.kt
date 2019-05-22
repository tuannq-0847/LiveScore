package com.sun.livescore.ui.standing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.livescore.R
import com.sun.livescore.data.model.standing.Standing
import com.sun.livescore.databinding.ItemLeagueStandingBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.ui.base.BaseViewHolder
import com.sun.livescore.ui.standing.StandingAdapter.StandingViewHolder
import kotlinx.android.synthetic.main.item_league_standing.view.itemStanding

class StandingAdapter(standings: List<Standing>) :
    BaseRecyclerAdapter<ItemLeagueStandingBinding, Standing, StandingViewHolder>(standings) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BaseViewHolder<ItemLeagueStandingBinding, Standing> {
        return StandingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), getLayoutRes(viewType), parent, false
            )
        )
    }

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_league_standing

    override fun getItemViewType(position: Int): Int {

        return when {
            position % 2 == 0 -> EVEN_POSITION
            else -> ODD_POSITION
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemLeagueStandingBinding, Standing>, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    inner class StandingViewHolder(binding: ItemLeagueStandingBinding) :
        BaseViewHolder<ItemLeagueStandingBinding, Standing>(binding) {

        override fun bindView(binding: ItemLeagueStandingBinding, position: Int, data: Standing) {
            when {
                position % 2 == 0 -> {
                    itemView.itemStanding.setBackgroundResource(R.color.color_background)
                }
                else -> {
                    itemView.itemStanding.setBackgroundResource(R.color.color_gray)
                }

            }
            binding.run {
                standing = data
            }
        }
    }

    companion object {
        const val ODD_POSITION = 1
        const val EVEN_POSITION = 0
    }
}
