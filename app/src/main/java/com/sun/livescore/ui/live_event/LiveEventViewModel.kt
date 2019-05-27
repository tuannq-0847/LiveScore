package com.sun.livescore.ui.live_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.livescore.data.model.event.Event
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.data.repository.LiveEventRepository
import com.sun.livescore.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LiveEventViewModel(private val liveEventRepository: LiveEventRepository) : BaseViewModel() {
    private val _liveEventLiveData = MutableLiveData<ApiResponse<List<Event>>>()
    val eventLiveData = MutableLiveData<Event>()
    val liveEventLiveData: LiveData<ApiResponse<List<Event>>>
        get() = _liveEventLiveData

    fun setEvent(event: Event) {
        eventLiveData.value = event
    }

    fun getLiveEvents(fixture: Fixture) {
        fixture.id?.run {
            compositeDisposable.add(
                liveEventRepository.getLiveEvents(this)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe {
                        _liveEventLiveData.value = ApiResponse.loading()
                    }
                    .map {
                        it.data?.events
                    }
                    .subscribe({
                        when {
                            it.isNullOrEmpty() -> emptyLiveData.value = true
                            else -> _liveEventLiveData.value = ApiResponse.success(it)
                        }
                    }, {
                        _liveEventLiveData.value = ApiResponse.error(it.message.toString())
                    })
            )
        }
    }
}
