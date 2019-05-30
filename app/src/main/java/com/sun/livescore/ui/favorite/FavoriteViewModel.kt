package com.sun.livescore.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.livescore.data.model.team.Team
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.data.repository.FavoriteLocalRepository
import com.sun.livescore.ui.base.BaseViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel(
    private val favoriteLocalRepository: FavoriteLocalRepository
) : BaseViewModel() {

    private val _favoriteTeams = MutableLiveData<ApiResponse<List<Team>>>()
    val favLiveData: LiveData<ApiResponse<List<Team>>>
        get() = _favoriteTeams
    private val _databaseLiveData = MutableLiveData<ApiResponse<Boolean>>()
    val dbLiveData: LiveData<ApiResponse<Boolean>>
        get() = _databaseLiveData

    fun getFavorites() {
        compositeDisposable.add(
            favoriteLocalRepository.getTeams()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    _favoriteTeams.value = ApiResponse.loading()
                }
                .subscribe({
                    _favoriteTeams.value = ApiResponse.success(it)
                }, {
                    _favoriteTeams.value = ApiResponse.error(it.message.toString())
                })
        )
    }

    fun searchTeamsByName(query: String?) {
        compositeDisposable.add(favoriteLocalRepository.queryTeams(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _favoriteTeams.value = ApiResponse.loading()
            }
            .subscribe({
                _favoriteTeams.value = ApiResponse.success(it)
            }, {
                _favoriteTeams.value = ApiResponse.error(it.message.toString())
            })
        )
    }

    fun saveFavoriteTeam(teamId: String) {
        compositeDisposable.add(
            Completable.fromAction {
                favoriteLocalRepository.saveFavoriteTeam(teamId)
            }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _databaseLiveData.value = ApiResponse.success(true)
                }, {
                    _databaseLiveData.value = ApiResponse.error(it.message.toString())
                })
        )
    }

    fun removeFavoriteTeam(teamId: String) {
        compositeDisposable.add(
            Completable.fromAction {
                favoriteLocalRepository.removeFavoriteTeam(teamId)
            }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _databaseLiveData.value = ApiResponse.success(true)
                }, {
                    _databaseLiveData.value = ApiResponse.error(it.message.toString())
                })
        )
    }
}
