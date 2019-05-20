package com.sun.livescore.di

import com.sun.livescore.data.remote.ScoreRemoteDataSource
import com.sun.livescore.ui.scores.child.ScoreChildViewModel
import com.sun.livescore.ui.scores.parent.ScoreViewModel
import com.sun.livescore.data.repository.ScoreRepository
import com.sun.livescore.ui.scores.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ScoreViewModel() }
    viewModel { ScoreChildViewModel(get()) }
    single { ScoreRemoteDataSource(get()) }
    single { ScoreRepository(get()) }
}
