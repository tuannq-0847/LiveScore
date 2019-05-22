package com.sun.livescore.data.remote.country

import com.sun.livescore.data.model.country.CountryResponse
import com.sun.livescore.data.remote.country.CountryDataSource.Remote
import com.sun.livescore.data.remote.request.ApiService
import io.reactivex.Single

class CountryRemoteDataSource(private val apiService: ApiService) :
    Remote {

    override fun getLeagueCountry(): Single<CountryResponse> = apiService.getCountries()
}
