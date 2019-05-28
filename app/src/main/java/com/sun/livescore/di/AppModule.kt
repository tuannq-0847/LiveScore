package com.sun.livescore.di

import com.sun.livescore.data.remote.country.CountryRemoteDataSource
import com.sun.livescore.data.remote.league.LeagueRemoteDataSource
import com.sun.livescore.data.remote.live.LiveEventRemoteDataSource
import com.sun.livescore.data.remote.score.ScoreRemoteDataSource
import com.sun.livescore.data.remote.standing.StandingRemoteDataSource
import com.sun.livescore.data.repository.CountryRepository
import com.sun.livescore.data.repository.LeagueRepository
import com.sun.livescore.data.repository.LiveEventRepository
import com.sun.livescore.data.repository.ScoreRepository
import com.sun.livescore.data.repository.StandingRepository
import com.sun.livescore.ui.country.CountryViewModel
import com.sun.livescore.ui.leagues.LeagueViewModel
import com.sun.livescore.ui.live_event.LiveEventViewModel
import com.sun.livescore.ui.scores.child.ScoreChildViewModel
import com.sun.livescore.ui.scores.live.LiveScoreViewModel
import com.sun.livescore.ui.scores.parent.ScoreViewModel
import com.sun.livescore.ui.standing.StandingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ScoreViewModel() }
    viewModel { CountryViewModel(get()) }
    viewModel { ScoreChildViewModel(get(), get()) }
    viewModel { LeagueViewModel(get()) }
    viewModel { StandingViewModel(get()) }
    viewModel { LiveScoreViewModel(get()) }
    viewModel { LiveEventViewModel(get()) }
    single { ScoreRemoteDataSource(get()) }
    single { ScoreRepository(get()) }
    single { CountryRemoteDataSource(get()) }
    single { CountryRepository(get()) }
    single { LeagueRemoteDataSource(get()) }
    single { LeagueRepository(get()) }
    single { StandingRepository(get()) }
    single { StandingRemoteDataSource(get()) }
    single { LiveEventRepository(get()) }
    single { LiveEventRemoteDataSource(get()) }
}
