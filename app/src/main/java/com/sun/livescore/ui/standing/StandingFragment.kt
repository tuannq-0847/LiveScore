package com.sun.livescore.ui.standing

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.livescore.R
import com.sun.livescore.data.model.EnumStatus.ERROR
import com.sun.livescore.data.model.EnumStatus.LOADING
import com.sun.livescore.data.model.EnumStatus.SUCCESS
import com.sun.livescore.data.model.league.League
import com.sun.livescore.data.model.standing.StandingResponse
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.fragment_league_standing.progressStanding
import kotlinx.android.synthetic.main.fragment_league_standing.recyclerLeagueStanding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StandingFragment : BaseFragment() {
    private val standingViewModel: StandingViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_league_standing

    override fun initComponents() {
        val bundle = arguments
        bundle?.let {
            val league = it.getParcelable<League>(ARGUMENT_COUNTRY)
            league?.id?.let { id ->
                standingViewModel.getStandings(id, SEASON)
            }
        }
        doObserve()
    }

    private fun doObserve() {
        standingViewModel.standingLiveData.observe(this, Observer {
            when (it.status) {
                LOADING -> showLoading(true)
                ERROR -> showError(it.message)
                SUCCESS -> showSuccess(it.data)
            }
        })
    }

    private fun showSuccess(data: StandingResponse?) {
        showLoading(false)
        data?.data?.tables?.let {
            val adapter = StandingAdapter(it)
            recyclerLeagueStanding.adapter = adapter
            recyclerLeagueStanding.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun showError(message: String) {
        context?.showMessage(message)
    }

    private fun showLoading(isLoading: Boolean) {
        progressStanding.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance(league: League) = StandingFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_COUNTRY, league)
            }
        }

        const val ARGUMENT_COUNTRY = "ARGUMENT_COUNTRY"
        const val SEASON = "2"
    }
}
