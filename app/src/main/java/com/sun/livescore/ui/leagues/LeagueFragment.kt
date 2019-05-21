package com.sun.livescore.ui.leagues

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.livescore.R
import com.sun.livescore.data.model.EnumStatus.ERROR
import com.sun.livescore.data.model.EnumStatus.LOADING
import com.sun.livescore.data.model.EnumStatus.SUCCESS
import com.sun.livescore.data.model.country.Country
import com.sun.livescore.data.model.league.LeagueResponse
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.fragment_leagues_in_country.progressLeagues
import kotlinx.android.synthetic.main.fragment_leagues_in_country.recyclerLeagues
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeagueFragment : BaseFragment() {
    private val leagueViewModel: LeagueViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_leagues_in_country

    override fun initComponents() {
        val country = arguments?.getParcelable<Country>(ARGUMENT_COUNTRY)
        country?.leagueLink?.let {
            leagueViewModel.getLeagues(splitLinkLeagues(it))
        }
        leagueViewModel.leagueLiveData.observe(this, Observer {
            when (it.status) {
                ERROR -> showError(it.message)
                LOADING -> showLoading(true)
                SUCCESS -> showSuccess(it.data)
            }
        })
    }

    private fun showSuccess(data: LeagueResponse?) {
        showLoading(false)
        displayLeaguesToView(data)
    }

    private fun displayLeaguesToView(data: LeagueResponse?) {
        val adapter = data?.data?.leagues?.let { LeagueAdapter(it) }
        recyclerLeagues.adapter = adapter
        recyclerLeagues.layoutManager = LinearLayoutManager(context)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) progressLeagues.visibility = View.VISIBLE else progressLeagues.visibility = View.GONE
    }

    private fun splitLinkLeagues(url: String): String {
        return url.split(REGEX)[1]
    }

    private fun showError(message: String) {
        context?.showMessage(message)
    }

    companion object {
        @JvmStatic
        fun newInstance(country: Country) = LeagueFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_COUNTRY, country)
            }
        }

        const val REGEX = "country="
        const val END_INDEX = 1
        const val ARGUMENT_COUNTRY = "ARGUMENT_COUNTRY"
    }
}
