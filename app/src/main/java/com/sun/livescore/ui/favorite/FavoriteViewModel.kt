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
) :
    BaseViewModel() {

    private val _favLiveData = MutableLiveData<ApiResponse<List<Team>>>()
    val favLiveData: LiveData<ApiResponse<List<Team>>>
        get() = _favLiveData
    private val _dbLiveData = MutableLiveData<ApiResponse<Boolean>>()
    val dbLiveData: LiveData<ApiResponse<Boolean>>
        get() = _dbLiveData

    fun getFavorites() {
        compositeDisposable.add(
            favoriteLocalRepository.getAllTeams()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    _favLiveData.value = ApiResponse.loading()
                }
                .subscribe({
                    _favLiveData.value = ApiResponse.success(it)
                }, {
                    _favLiveData.value = ApiResponse.error(it.message.toString())
                })
        )
    }

    fun searchLeaguesByName(query: String?) {
        compositeDisposable.add(favoriteLocalRepository.getAllTeams()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _favLiveData.value = ApiResponse.loading()
            }
            .map {
                query?.let { query ->
                    it.filter { team ->
                        if (team.name != null) {
                            team.name!!.toLowerCase().contains(query.toLowerCase())
                        } else {
                            false
                        }

                    }
                }
            }
            .subscribe({
                _favLiveData.value = ApiResponse.success(it)
            }, {
                _favLiveData.value = ApiResponse.error(it.message.toString())
            })
        )
    }

    fun onListenerFollowClub(team: Team) {
        compositeDisposable.add(
            Completable.fromAction {
                favoriteLocalRepository.saveFavTeam(team)
            }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _dbLiveData.value = ApiResponse.success(true)
                }, {
                    _dbLiveData.value = ApiResponse.error(it.message.toString())
                })
        )
    }
}
