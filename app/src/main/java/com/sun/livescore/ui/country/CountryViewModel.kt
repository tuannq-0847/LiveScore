package com.sun.livescore.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.livescore.data.model.country.Country
import com.sun.livescore.data.model.country.CountryResponse
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.data.repository.CountryRepository
import com.sun.livescore.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CountryViewModel(private val repository: CountryRepository) : BaseViewModel() {
    private val _countriesLiveData = MutableLiveData<ApiResponse<CountryResponse>>()
    val countriesLiveData: LiveData<ApiResponse<CountryResponse>>
        get() = _countriesLiveData
    private val _countriesSearchLiveData = MutableLiveData<ApiResponse<List<Country>>>()
    val countriesSearchLiveData: LiveData<ApiResponse<List<Country>>>
        get() = _countriesSearchLiveData
    private val _countryLiveData = MutableLiveData<Country>()
    val countryLiveData: LiveData<Country>
        get() = _countryLiveData

    fun getLeagueCountry() {
        compositeDisposable.add(repository.getLeagueCountry()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _countriesLiveData.value = ApiResponse.loading()
            }
            .subscribe({
                _countriesLiveData.value = ApiResponse.success(it)
            }, {
                _countriesLiveData.value = ApiResponse.error(it.message.toString())
            })
        )
    }

    fun onCountryClick(country: Country) {
        _countryLiveData.value = country
    }

    fun searchLeaguesByName(query: String?) {
        compositeDisposable.add(repository.getLeagueCountry()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _countriesSearchLiveData.value = ApiResponse.loading()
            }
            .map {
                query?.let { query ->
                    it.data?.countries?.filter {
                        it.nameLeague!!.toLowerCase().contains(query.toLowerCase())
                    } as List<Country>
                }
            }
            .subscribe({
                when {
                    it.isNullOrEmpty() -> emptyLiveData.value = true
                    else -> _countriesSearchLiveData.value = ApiResponse.success(it)
                }
            }, {
                _countriesSearchLiveData.value = ApiResponse.error(it.message.toString())
            })
        )
    }
}
