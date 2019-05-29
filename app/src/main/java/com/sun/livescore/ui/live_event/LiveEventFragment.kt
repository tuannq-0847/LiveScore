package com.sun.livescore.ui.live_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.livescore.R
import com.sun.livescore.data.model.EnumStatus.ERROR
import com.sun.livescore.data.model.EnumStatus.LOADING
import com.sun.livescore.data.model.EnumStatus.SUCCESS
import com.sun.livescore.data.model.event.Event
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.databinding.FragmentLiveEventBinding
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.util.Constant
import com.sun.livescore.util.ContextExtension.showMessage
import com.sun.livescore.util.Util
import kotlinx.android.synthetic.main.fragment_live_event.imageEmpty
import kotlinx.android.synthetic.main.fragment_live_event.progressLiveEvent
import kotlinx.android.synthetic.main.fragment_live_event.recyclerLiveEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class LiveEventFragment : BaseFragment() {
    private val liveEventViewModel: LiveEventViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_live_event

    override fun initComponents() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLiveEventBinding.inflate(
            inflater, container, false
        )
        val fixture = arguments?.getParcelable<Fixture>(ARGUMENT_FIXTURE)
        fixture?.run {
            binding.fixture = this
            liveEventViewModel.getLiveEvents(this)
            checkEmpty()
            if (this is History) {
                binding.awayScore = Util.getScoreFromString(this.score)[Constant.SECOND_SCORE_INDEX]
                binding.homeScore = Util.getScoreFromString(this.score)[Constant.FIRST_SCORE_INDEX]
            }
        }
        doObserve()

        return binding.root
    }

    private fun checkEmpty() {
        liveEventViewModel.emptyLiveData.observe(this, Observer {
            imageEmpty.visibility = View.VISIBLE
        })
    }

    private fun doObserve() {
        liveEventViewModel.liveEventLiveData.observe(this, Observer {
            when (it.status) {
                SUCCESS -> showSuccess(it.data)
                ERROR -> showError(it.message)
                LOADING -> showLoading(true)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        progressLiveEvent.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        context?.showMessage(message)
    }

    private fun showSuccess(data: List<Event>?) {
        showLoading(false)
        data?.run {
            val adapter = LiveEventAdapter(this)
            recyclerLiveEvent.adapter = adapter
            recyclerLiveEvent.layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(fixture: Fixture) = LiveEventFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_FIXTURE, fixture)
            }
        }

        private const val ARGUMENT_FIXTURE = "ARGUMENT_ID_MATCH"
    }
}
