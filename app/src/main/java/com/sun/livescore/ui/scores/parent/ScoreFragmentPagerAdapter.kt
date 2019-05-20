package com.sun.livescore.ui.scores.parent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sun.livescore.R.layout
import com.sun.livescore.data.model.DateTime
import com.sun.livescore.ui.scores.child.ScoresChildFragment
import kotlinx.android.synthetic.main.custom_tab_item.view.textDate
import kotlinx.android.synthetic.main.custom_tab_item.view.textDay

class ScoreFragmentPagerAdapter(
    fm: FragmentManager, private val context: Context,
    private val dateTimes: List<DateTime>
) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ScoresChildFragment()
    }

    override fun getCount(): Int {
        return dateTimes.size
    }

    fun getTabView(position: Int): View {
        val view = LayoutInflater.from(context).inflate(layout.custom_tab_item, null)
        val dateTime = dateTimes[position]
        view.textDay.text = dateTime.day
        view.textDate.text = dateTime.date
        return view
    }
}
