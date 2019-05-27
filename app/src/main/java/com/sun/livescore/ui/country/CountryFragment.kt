package com.sun.livescore.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.livescore.R
import com.sun.livescore.data.model.EnumStatus.ERROR
import com.sun.livescore.data.model.EnumStatus.LOADING
import com.sun.livescore.data.model.EnumStatus.SUCCESS
import com.sun.livescore.data.model.country.Country
import com.sun.livescore.data.model.country.CountryResponse
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.ui.leagues.LeagueFragment
import com.sun.livescore.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.league_country_fragment.progressLeague
import kotlinx.android.synthetic.main.league_country_fragment.recyclerLeague
import kotlinx.android.synthetic.main.league_country_fragment.searchLeagues
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryFragment : BaseFragment(), OnQueryTextListener {
    private val countryViewModel: CountryViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.league_country_fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun initComponents() {
        countryViewModel.getLeagueCountry()
        countryViewModel.countriesLiveData.observe(this, Observer {
            handleLeaguesResponse(it)
        })
        countryViewModel.countriesSearchLiveData.observe(this, Observer {
            handleSearchResult(it)
        })
        countryViewModel.countryLiveData.observe(this, Observer {
            val leagueFragment = LeagueFragment.newInstance(it)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.layoutParent, leagueFragment)
                ?.addToBackStack(null)
                ?.commit()
        })
    }

    private fun handleSearchResult(apiResponse: ApiResponse<List<Country>>) {
        when (apiResponse.status) {
            SUCCESS -> showSuccessSearching(apiResponse.data)
            LOADING -> showLoading(true)
            ERROR -> showError(apiResponse.message)
        }
    }

    private fun showSuccessSearching(data: List<Country>?) {
        showLoading(false)
        displaySearchResult(data)
    }

    private fun displaySearchResult(data: List<Country>?) {
        val adapter = data?.let { CountryAdapter(it, countryViewModel) }
        recyclerLeague.layoutManager = LinearLayoutManager(context)
        recyclerLeague.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editText = searchLeagues.findViewById<AutoCompleteTextView>(R.id.search_src_text)
        context?.let {
            editText.setHintTextColor(ContextCompat.getColor(it, R.color.color_white))
            editText.setTextColor(ContextCompat.getColor(it, R.color.color_white))
            searchLeagues.setOnQueryTextListener(this)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            countryViewModel.searchLeaguesByName(newText)
        }
        return true
    }

    private fun handleLeaguesResponse(apiResponse: ApiResponse<CountryResponse>) {
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

    private fun showSuccess(data: CountryResponse?) {
        showLoading(false)
        displayDataToLeagues(data)
    }

    fun displayDataToLeagues(data: CountryResponse?) {
        val adapter = data?.data?.countries?.let { CountryAdapter(it, countryViewModel) }
        recyclerLeague.layoutManager = LinearLayoutManager(context)
        recyclerLeague.adapter = adapter
    }
}
