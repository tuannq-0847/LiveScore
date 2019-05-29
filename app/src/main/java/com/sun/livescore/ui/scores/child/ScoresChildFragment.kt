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
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.data.remote.response.ApiResponse
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.ui.live_event.LiveEventFragment
import com.sun.livescore.ui.scores.SharedViewModel
import com.sun.livescore.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.fragment_scores_child.progressLoading
import kotlinx.android.synthetic.main.fragment_scores_child.recyclerScores
import org.koin.androidx.viewmodel.ext.android.viewModel

open class ScoresChildFragment : BaseFragment() {
    open var sharedViewModel: SharedViewModel? = null
    open val scoreChildViewModel: ScoreChildViewModel by viewModel()
    private var scoreHistoryAdapter: ScoreHistoryAdapter? = null
    private var scoreFixtureAdapter: ScoreFixtureAdapter? = null
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
        doObserve()
    }

    private fun doObserve() {
        scoreChildViewModel.scoreHistoryLiveData.observe(this, Observer { apiResponse ->
            handleHistoryResponse(apiResponse)
        })
        scoreChildViewModel.scoreFixtureLiveData.observe(this, Observer { apiResponse ->
            handleFixtureResponse(apiResponse)
        })
        scoreChildViewModel.matchLiveData.observe(this, Observer {
            val liveEventFragment = LiveEventFragment.newInstance(it)
            activity?.run {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.layoutParent, liveEventFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
    }

    private fun handleHistoryResponse(apiResponse: ApiResponse<List<History>>) {
        when (apiResponse.status) {
            LOADING -> showLoading(true)
            ERROR -> showError(apiResponse.message)
            SUCCESS -> displayHistory(apiResponse.data)
        }
    }

    private fun handleFixtureResponse(apiResponse: ApiResponse<List<Fixture>>) {
        when (apiResponse.status) {
            LOADING -> showLoading(true)
            ERROR -> showError(apiResponse.message)
            SUCCESS -> displayFixture(apiResponse.data)
        }
    }

    private fun displayHistory(data: List<History>?) {
        showLoading(false)
        data?.let { loadHistoryToView(data) }
    }

    private fun displayFixture(data: List<Fixture>?) {
        showLoading(false)
        data?.let { loadFixtureToView(data) }
    }

    private fun loadHistoryToView(data: List<History>?) {
        data?.let {
            scoreHistoryAdapter = ScoreHistoryAdapter(it, scoreChildViewModel)
            recyclerScores.layoutManager = LinearLayoutManager(context)
            recyclerScores.adapter = scoreHistoryAdapter
        }
    }

    private fun loadFixtureToView(data: List<Fixture>?) {
        data?.let {
            scoreFixtureAdapter = ScoreFixtureAdapter(it, scoreChildViewModel)
            recyclerScores.layoutManager = LinearLayoutManager(context)
            recyclerScores.adapter = scoreFixtureAdapter
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        showLoading(true)
        scoreHistoryAdapter?.clearData()
        scoreFixtureAdapter?.clearData()
    }

    private fun showError(message: String) {
        context?.showMessage(message)
    }

    private fun showLoading(isLoading: Boolean) {
        progressLoading?.run {
            visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    inner class ScoreObserver : Observer<String> {
        override fun onChanged(t: String?) {
            t?.let {
                scoreChildViewModel.getScores(it)
            }
        }
    }
}
