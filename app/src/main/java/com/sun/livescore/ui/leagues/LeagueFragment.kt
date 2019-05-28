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
import com.sun.livescore.data.model.league.League
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.ui.standing.StandingFragment
import com.sun.livescore.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.fragment_leagues_in_country.imageEmptyCountry
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
        checkIsEmpty()
        leagueViewModel.leagueLiveData.observe(this, Observer {
            when (it.status) {
                ERROR -> showError(it.message)
                LOADING -> showLoading(true)
                SUCCESS -> showSuccess(it.data)
            }
        })
        receiveObserve()
    }

    private fun checkIsEmpty() {
        leagueViewModel.emptyLiveData.observe(this, Observer {
            imageEmptyCountry.visibility = View.VISIBLE
        })
    }

    private fun receiveObserve() {
        leagueViewModel.leagueListenerLiveData.observe(this, Observer {
            val standingFragment = StandingFragment.newInstance(it)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.layoutParent, standingFragment)
                ?.addToBackStack(null)
                ?.commit()
        })
    }

    private fun showSuccess(data: List<League>?) {
        showLoading(false)
        displayLeaguesToView(data)
    }

    private fun displayLeaguesToView(data: List<League>?) {
        data?.let {
            val adapter = LeagueAdapter(it, leagueViewModel)
            recyclerLeagues.adapter = adapter
            recyclerLeagues.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progressLeagues.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun splitLinkLeagues(url: String): String {
        return url.split(REGEX)[END_INDEX]
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
