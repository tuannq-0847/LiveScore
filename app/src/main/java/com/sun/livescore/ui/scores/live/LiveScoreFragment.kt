package com.sun.livescore.ui.scores.live

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.livescore.R
import com.sun.livescore.data.model.EnumStatus.ERROR
import com.sun.livescore.data.model.EnumStatus.LOADING
import com.sun.livescore.data.model.EnumStatus.SUCCESS
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.fragment_scores_child.imageEmptyChild
import kotlinx.android.synthetic.main.fragment_scores_child.progressLoading
import kotlinx.android.synthetic.main.fragment_scores_child.recyclerScores
import org.koin.androidx.viewmodel.ext.android.viewModel

class LiveScoreFragment : BaseFragment() {
    private val liveScoreViewModel: LiveScoreViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_scores_child

    override fun initComponents() {
        liveScoreViewModel.getLiveScore()
        checkIsEmpty()
        liveScoreViewModel.liveScoreLiveData.observe(this, Observer {
            when (it.status) {
                SUCCESS -> showSuccess(it.data)
                ERROR -> showError(it.message)
                LOADING -> showLoading(true)
            }
        })
    }

    private fun checkIsEmpty() {
        liveScoreViewModel.emptyLiveData.observe(this, Observer {
            imageEmptyChild.visibility = View.VISIBLE
        })
    }

    private fun showSuccess(data: List<History>?) {
        showLoading(false)
        loadDataToView(data)
    }

    private fun loadDataToView(data: List<History>?) {
        data?.let {

            val adapter = LiveScoreAdapter(it)
            recyclerScores.adapter = adapter
            recyclerScores.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun showError(message: String) {
        context?.showMessage(message)
    }

    private fun showLoading(isLoading: Boolean) {
        progressLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
