package com.sun.livescore.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.livescore.R
import com.sun.livescore.data.model.country.Country
import com.sun.livescore.databinding.ItemLeagueCountryBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.ui.base.BaseViewHolder
import com.sun.livescore.ui.country.CountryAdapter.CountryViewHolder

class CountryAdapter(private val countries: List<Country>, private val viewModel: CountryViewModel) :
    BaseRecyclerAdapter<ItemLeagueCountryBinding, Country, CountryViewHolder>(countries) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_league_country

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), getLayoutRes(viewType), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class CountryViewHolder(binding: ItemLeagueCountryBinding) :
        BaseViewHolder<ItemLeagueCountryBinding, Country>(binding) {

        override fun bindView(binding: ItemLeagueCountryBinding, position: Int, score: Country) {
            binding.run {
                this.country = score
                this.viewmodel = viewModel
            }
        }
    }
}
