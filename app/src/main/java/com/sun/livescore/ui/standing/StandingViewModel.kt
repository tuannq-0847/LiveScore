package com.sun.livescore.ui.standing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.livescore.data.model.standing.StandingResponse
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.data.repository.StandingRepository
import com.sun.livescore.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StandingViewModel(private val standingRepository: StandingRepository) : BaseViewModel() {
    private val _standingLiveData = MutableLiveData<ApiResponse<StandingResponse>>()
    val standingLiveData: LiveData<ApiResponse<StandingResponse>>
        get() = _standingLiveData
    fun getStandings(leagueId: String, seasonId: String) {
        compositeDisposable.add(standingRepository.getStanding(leagueId, seasonId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _standingLiveData.value = ApiResponse.loading()
            }
            .subscribe({
                _standingLiveData.value = ApiResponse.success(it)
            }, {
                _standingLiveData.value = ApiResponse.error(it.message.toString())
            })
        )
    }
}
