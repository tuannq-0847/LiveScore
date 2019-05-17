package com.sun.livescore.ui.scores.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.livescore.R
import com.sun.livescore.data.model.EnumStatus.ERROR
import com.sun.livescore.data.model.EnumStatus.LOADING
import com.sun.livescore.data.model.EnumStatus.SUCCESS
import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.ui.scores.SharedViewModel
import com.sun.livescore.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.fragment_scores_child.progressLoading
import kotlinx.android.synthetic.main.fragment_scores_child.recyclerScores
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScoresChildFragment : BaseFragment() {
    private var sharedViewModel: SharedViewModel? = null
    private val scoreChildViewModel: ScoreChildViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_scores_child

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun initComponents() {
        sharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception()
        sharedViewModel?.dateLiveData?.observe(this, ScoreObserver())
        scoreChildViewModel.scoreHistoryLiveData.observe(this, Observer { apiResponse ->
            handleResponse(apiResponse)
        })
        scoreChildViewModel.scoreFixtureLiveData.observe(this, Observer { apiResponse ->
            handleResponse(apiResponse)
        })
    }

    private fun handleResponse(apiResponse: ApiResponse<*>) {
        when (apiResponse.status) {
            LOADING -> showLoading(true)
            ERROR -> showError(apiResponse.message)
            SUCCESS -> showSuccess(apiResponse.data)
        }
    }

    private fun showSuccess(data: Any?) {
        showLoading(false)
        data?.let {
            handleData(it)
        }
    }

    private fun handleData(data: Any) {
        when (data) {
            is HistoryResponse -> {
                loadHistoryToView(data)
            }
            is FixtureResponse -> {
                loadFixtureToView(data)
            }
        }
    }

    private fun loadHistoryToView(data: HistoryResponse) {
        data.data?.histories?.let {
            val adapter = ScoreHistoryAdapter(it)
            recyclerScores.layoutManager = LinearLayoutManager(context)
            recyclerScores.adapter = adapter
        }
    }

    private fun loadFixtureToView(data: FixtureResponse) {
        data.data?.fixtures?.let {
            val adapter = ScoreFixtureAdapter(it)
            recyclerScores.layoutManager = LinearLayoutManager(context)
            recyclerScores.adapter = adapter
        }
    }

    private fun showError(message: String) {
        context?.showMessage(message)
    }

    private fun showLoading(isLoading: Boolean) {
        progressLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    inner class ScoreObserver : Observer<String> {
        override fun onChanged(t: String?) {
            t?.let {
                scoreChildViewModel.getScores(it)
            }
        }
    }
}
