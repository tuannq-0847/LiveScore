package com.sun.livescore.data.remote

import com.sun.livescore.data.model.leagues.LeagueResponse
import com.sun.livescore.data.remote.request.ApiService
import io.reactivex.Single

class LeagueRemoteDataSource(private val apiService: ApiService) : LeagueDataSource.Remote {

    override fun getLeagueCountry(): Single<LeagueResponse> = apiService.getLeagues()
}
