package com.sun.livescore.ui.leagues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.livescore.R
import com.sun.livescore.data.model.leagues.LeagueCountry
import com.sun.livescore.databinding.ItemLeagueCountryBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.ui.base.BaseViewHolder
import com.sun.livescore.ui.leagues.LeagueAdapter.LeagueViewHolder

class LeagueAdapter(private val leagueCountries: List<LeagueCountry>) :
    BaseRecyclerAdapter<ItemLeagueCountryBinding, LeagueCountry, LeagueViewHolder>(leagueCountries) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_league_country

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), getLayoutRes(viewType), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return leagueCountries.size
    }

    class LeagueViewHolder(binding: ItemLeagueCountryBinding) :
        BaseViewHolder<ItemLeagueCountryBinding, LeagueCountry>(binding) {

        override fun bindView(binding: ItemLeagueCountryBinding, position: Int, score: LeagueCountry) {
            binding.run {
                this.leagueCountry = score
            }
        }
    }
}
