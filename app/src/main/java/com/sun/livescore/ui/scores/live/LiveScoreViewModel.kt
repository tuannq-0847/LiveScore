package com.sun.livescore.ui.scores.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.data.repository.ScoreRepository
import com.sun.livescore.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LiveScoreViewModel(private val repository: ScoreRepository) : BaseViewModel() {
    private val _liveScoreLiveData = MutableLiveData<ApiResponse<List<History>>>()
    val liveScoreLiveData: LiveData<ApiResponse<List<History>>>
        get() = _liveScoreLiveData

    fun getLiveScore() {
        compositeDisposable.add(
            repository.getLiveScores()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    _liveScoreLiveData.value = ApiResponse.loading()
                }
                .map {
                    it.data?.histories?.filter {
                        it.status == IN_PLAY || it.status == ADDED_TIME || it.status == HALF_TIME_BREAK
                    } as List<History>
                }
                .subscribe({
                    _liveScoreLiveData.value = ApiResponse.success(it)
                }, {
                    _liveScoreLiveData.value = ApiResponse.error(it.message.toString())
                })
        )
    }

    companion object {
        const val IN_PLAY = "IN PLAY"
        const val ADDED_TIME = "ADDED TIME"
        const val HALF_TIME_BREAK = "HALF TIME BREAK"
    }
}
