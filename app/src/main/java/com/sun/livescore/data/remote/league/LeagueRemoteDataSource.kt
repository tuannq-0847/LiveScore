package com.sun.livescore.data.remote.league

import com.sun.livescore.data.model.league.LeagueResponse
import com.sun.livescore.data.remote.league.LeagueDataSource.Remote
import com.sun.livescore.data.remote.request.ApiService
import io.reactivex.Single

class LeagueRemoteDataSource(private val apiService: ApiService) :
    Remote {
    override fun getLeagues(country: String): Single<LeagueResponse> = apiService.getLeagues(country)
}
