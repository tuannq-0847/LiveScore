package com.sun.livescore.di

import com.sun.livescore.data.remote.CountryRemoteDataSource
import com.sun.livescore.data.remote.LeagueRemoteDataSource
import com.sun.livescore.data.remote.ScoreRemoteDataSource
import com.sun.livescore.data.repository.CountryRepository
import com.sun.livescore.data.repository.LeagueRepository
import com.sun.livescore.ui.scores.child.ScoreChildViewModel
import com.sun.livescore.ui.scores.parent.ScoreViewModel
import com.sun.livescore.data.repository.ScoreRepository
import com.sun.livescore.ui.country.CountryViewModel
import com.sun.livescore.ui.leagues.LeagueViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ScoreViewModel() }
    viewModel { CountryViewModel(get()) }
    viewModel { ScoreChildViewModel(get()) }
    viewModel { LeagueViewModel(get()) }
    single { ScoreRemoteDataSource(get()) }
    single { ScoreRepository(get()) }
    single { CountryRemoteDataSource(get()) }
    single { CountryRepository(get()) }
    single { LeagueRemoteDataSource(get()) }
    single { LeagueRepository(get()) }
}
