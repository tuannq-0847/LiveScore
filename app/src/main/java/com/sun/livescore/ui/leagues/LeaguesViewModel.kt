package com.sun.livescore.ui.leagues

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.livescore.data.model.leagues.LeagueCountry
import com.sun.livescore.data.model.leagues.LeagueResponse
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.data.repository.LeagueRepository
import com.sun.livescore.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LeaguesViewModel(private val repository: LeagueRepository) : BaseViewModel() {
    private val _leaguesLiveData = MutableLiveData<ApiResponse<LeagueResponse>>()
    val leaguesLiveData: LiveData<ApiResponse<LeagueResponse>>
        get() = _leaguesLiveData
    private val _leaguesSearchLiveData = MutableLiveData<ApiResponse<List<LeagueCountry>>>()
    val leaguesSearchLiveData: LiveData<ApiResponse<List<LeagueCountry>>>
        get() = _leaguesSearchLiveData

    fun getLeagueCountry() {
        compositeDisposable.add(repository.getLeagueCountry()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _leaguesLiveData.value = ApiResponse.loading()
            }
            .subscribe({
                _leaguesLiveData.value = ApiResponse.success(it)
            }, {
                _leaguesLiveData.value = ApiResponse.error(it.message.toString())
            })
        )
    }

    fun searchLeaguesByName(query: String?) {
        compositeDisposable.add(repository.getLeagueCountry()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _leaguesSearchLiveData.value = ApiResponse.loading()
            }
            .subscribe({
                val leagueCountries = it.data?.leagueCountries?.filter {
                    it.nameLeague!!.toLowerCase().contains(query!!.toLowerCase())
                } as List<LeagueCountry>
                _leaguesSearchLiveData.value = ApiResponse.success(leagueCountries)
            }, {
                _leaguesSearchLiveData.value = ApiResponse.error(it.message.toString())
            })
        )
    }
}
