package com.sun.livescore.ui.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.livescore.R
import com.sun.livescore.data.model.EnumStatus.ERROR
import com.sun.livescore.data.model.EnumStatus.LOADING
import com.sun.livescore.data.model.EnumStatus.SUCCESS
import com.sun.livescore.data.model.leagues.LeagueCountry
import com.sun.livescore.data.model.leagues.LeagueResponse
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.league_country_fragment.progressLeague
import kotlinx.android.synthetic.main.league_country_fragment.recyclerLeague
import kotlinx.android.synthetic.main.league_country_fragment.searchLeagues
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaguesFragment : BaseFragment(), OnQueryTextListener {
    private val leaguesViewModel: LeaguesViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.league_country_fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun initComponents() {
        leaguesViewModel.getLeagueCountry()
        leaguesViewModel.leaguesLiveData.observe(this, Observer {
            handleLeaguesResponse(it)
        })
        leaguesViewModel.leaguesSearchLiveData.observe(this, Observer {
            handleSearchResult(it)
        })
    }

    private fun handleSearchResult(apiResponse: ApiResponse<List<LeagueCountry>>) {
        when (apiResponse.status) {
            SUCCESS -> showSuccessSearching(apiResponse.data)
            LOADING -> showLoading(true)
            ERROR -> showError(apiResponse.message)
        }
    }

    private fun showSuccessSearching(data: List<LeagueCountry>?) {
        showLoading(false)
        displaySearchResult(data)
    }

    private fun displaySearchResult(data: List<LeagueCountry>?) {
        val adapter = data?.let { LeagueAdapter(it) }
        recyclerLeague.layoutManager = LinearLayoutManager(context)
        recyclerLeague.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchLeagues.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            leaguesViewModel.searchLeaguesByName(newText)
        }
        return true
    }

    private fun handleLeaguesResponse(apiResponse: ApiResponse<LeagueResponse>) {
        when (apiResponse.status) {
            SUCCESS -> showSuccess(apiResponse.data)
            LOADING -> showLoading(true)
            ERROR -> showError(apiResponse.message)
        }
    }

    private fun showError(message: String) {
        context?.showMessage(message)
    }

    private fun showLoading(isLoading: Boolean) {
        progressLeague.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showSuccess(data: LeagueResponse?) {
        showLoading(false)
        displayDataToLeagues(data)
    }

    fun displayDataToLeagues(data: LeagueResponse?) {
        val adapter = data?.data?.leagueCountries?.let { LeagueAdapter(it) }
        recyclerLeague.layoutManager = LinearLayoutManager(context)
        recyclerLeague.adapter = adapter
    }
}
