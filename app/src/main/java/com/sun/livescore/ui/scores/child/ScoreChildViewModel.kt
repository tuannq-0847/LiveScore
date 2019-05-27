package com.sun.livescore.ui.scores.child

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.data.repository.ScoreRepository
import com.sun.livescore.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ScoreChildViewModel(private val repository: ScoreRepository) : BaseViewModel() {
    private val _scoreFixtureLiveData = MutableLiveData<ApiResponse<FixtureResponse>>()
    private val _scoreHistoryLiveData = MutableLiveData<ApiResponse<HistoryResponse>>()
    private val _matchLiveData = MutableLiveData<Fixture>()
    val matchLiveData: LiveData<Fixture>
        get() = _matchLiveData
    val scoreFixtureLiveData: LiveData<ApiResponse<FixtureResponse>>
        get() = _scoreFixtureLiveData
    val scoreHistoryLiveData: LiveData<ApiResponse<HistoryResponse>>
        get() = _scoreHistoryLiveData

    fun getScores(date: String) {
        val finalDate = getFinalDate(date, getCurrentDate())
        if (compareDateToFetchData(date, getCurrentDate())) {
            getScoreFixtures(finalDate)
        } else {
            getScoreHistories(finalDate)
        }
    }

    private fun getScoreFixtures(finalDate: String) {
        compositeDisposable.add(
            repository.getScoresFixtures(finalDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _scoreFixtureLiveData.value = ApiResponse.loading()
                }
                .subscribe({
                    _scoreFixtureLiveData.value = ApiResponse.success(it)
                }, {
                    _scoreFixtureLiveData.value = ApiResponse.error(it.message.toString())
                })
        )
    }

    private fun getScoreHistories(finalDate: String) {
        compositeDisposable.add(
            repository.getScoresHistory(finalDate, finalDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _scoreHistoryLiveData.value = ApiResponse.loading()
                }
                .subscribe({
                    _scoreHistoryLiveData.value = ApiResponse.success(it)
                }, {
                    _scoreHistoryLiveData.value = ApiResponse.error(it.message.toString())
                })
        )
    }

    private fun getCurrentDate(): String {
        val currentDateTime = System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat(dateFormatSyntax, Locale.US)
        return simpleDateFormat.format(currentDateTime)
    }

    fun onMatchClick(fixture: Fixture) {
        _matchLiveData.value = fixture
    }

    private fun getFinalDate(date: String, currentDate: String): String {
        val dateFormat = SimpleDateFormat(dateTimeFormatSyntax, Locale.US)
        val index = date.toInt() - currentDate.toInt()
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, index)
        return dateFormat.format(calendar.time)
    }

    private fun compareDateToFetchData(date: String, currentDate: String): Boolean {
        return date.toInt() >= currentDate.toInt()
    }

    companion object {
        const val dateFormatSyntax = "dd"
        const val dayFormatSyntax = "EE"
        const val dateTimeFormatSyntax = "yyyy-MM-dd"
    }
}
