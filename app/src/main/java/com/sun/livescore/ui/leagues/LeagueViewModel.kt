package com.sun.livescore.ui.leagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.livescore.data.model.league.League
import com.sun.livescore.data.model.league.LeagueResponse
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.data.repository.LeagueRepository
import com.sun.livescore.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LeagueViewModel(private val repository: LeagueRepository) : BaseViewModel() {
    private val _leagueLiveData = MutableLiveData<ApiResponse<LeagueResponse>>()
    val leagueLiveData: LiveData<ApiResponse<LeagueResponse>>
        get() = _leagueLiveData

    private val _leagueListenerLiveData = MutableLiveData<League>()
    val leagueListenerLiveData: LiveData<League>
        get() = _leagueListenerLiveData

    fun onLeagueClick(league: League) {
        _leagueListenerLiveData.value = league
    }

    fun getLeagues(country: String) {
        compositeDisposable.add(
            repository.getLeagues(country)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    _leagueLiveData.value = ApiResponse.loading()
                }
                .subscribe({
                    _leagueLiveData.value = ApiResponse.success(it)
                }, {
                    _leagueLiveData.value = ApiResponse.error(it.message.toString())
                })
        )
    }
}
