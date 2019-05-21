package com.sun.livescore.ui.leagues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.livescore.R
import com.sun.livescore.data.model.league.League
import com.sun.livescore.databinding.ItemLeaguesInCountryBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.ui.base.BaseViewHolder
import com.sun.livescore.ui.leagues.LeagueAdapter.LeagueViewHolder

class LeagueAdapter(private val leagues: List<League>) :
    BaseRecyclerAdapter<ItemLeaguesInCountryBinding, League, LeagueViewHolder>(leagues) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_leagues_in_country

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), getLayoutRes(viewType), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return leagues.size
    }

    inner class LeagueViewHolder(binding: ItemLeaguesInCountryBinding) :
        BaseViewHolder<ItemLeaguesInCountryBinding, League>(binding) {

        override fun bindView(binding: ItemLeaguesInCountryBinding, position: Int, score: League) {
            binding.run {
                league = score
            }
        }
    }
}
