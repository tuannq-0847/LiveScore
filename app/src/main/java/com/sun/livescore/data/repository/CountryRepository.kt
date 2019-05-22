package com.sun.livescore.data.repository

import com.sun.livescore.data.model.country.CountryResponse
import com.sun.livescore.data.remote.country.CountryDataSource
import com.sun.livescore.data.remote.country.CountryRemoteDataSource
import io.reactivex.Single

class CountryRepository(private val countryRemoteDataSource: CountryRemoteDataSource) : CountryDataSource.Remote {
    override fun getLeagueCountry(): Single<CountryResponse> = countryRemoteDataSource.getLeagueCountry()
}
