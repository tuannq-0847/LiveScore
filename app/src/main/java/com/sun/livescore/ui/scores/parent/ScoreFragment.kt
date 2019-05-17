package com.sun.livescore.ui.scores.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab
import com.sun.livescore.R
import com.sun.livescore.data.model.DateTime
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.ui.scores.SharedViewModel
import kotlinx.android.synthetic.main.fragment_scores.tabLayout
import kotlinx.android.synthetic.main.fragment_scores.viewPagerScores
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScoreFragment : BaseFragment(), OnTabSelectedListener {
    private val scoreViewModel: ScoreViewModel by viewModel()
    private var model: SharedViewModel? = null
    private val dates: List<DateTime> by lazy { scoreViewModel.handleDate() }
    override val layoutId: Int
        get() = R.layout.fragment_scores

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun initComponents() {
        model = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val pagerAdapter =
            context?.let { context ->
                activity?.supportFragmentManager?.let {
                    ScoreFragmentPagerAdapter(
                        it,
                        context, dates
                    )
                }
            }
        viewPagerScores.adapter = pagerAdapter
        setUpTabLayout(pagerAdapter)
    }

    private fun setUpTabLayout(pagerAdapter: ScoreFragmentPagerAdapter?) {
        tabLayout.addOnTabSelectedListener(this)
        tabLayout.setupWithViewPager(viewPagerScores)
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            tab?.customView = pagerAdapter?.getTabView(i)
        }
    }

    override fun onTabReselected(tab: Tab?) {
        val positionTab = tab?.position
        positionTab?.let {
            model?.setDate(dates[it].date)
        }
    }

    override fun onTabUnselected(tab: Tab?) {
    }

    override fun onTabSelected(tab: Tab?) {
        val positionTab = tab?.position
        positionTab?.let {
            model?.setDate(dates[it].date)
        }
    }
}
