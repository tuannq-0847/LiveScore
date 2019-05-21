package com.sun.livescore.ui

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sun.livescore.R
import com.sun.livescore.R.layout
import com.sun.livescore.ui.base.BaseActivity
import com.sun.livescore.ui.country.CountryFragment
import com.sun.livescore.ui.scores.parent.ScoreFragment
import kotlinx.android.synthetic.main.activity_main.bottomMain

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override val layoutId: Int
        get() = layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openScoresFragment()
    }

    override fun initComponents() {
        bottomMain.setOnNavigationItemSelectedListener(this)
    }

    private fun openScoresFragment() {
        supportFragmentManager
            .beginTransaction().add(R.id.layoutParent, ScoreFragment())
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuScores -> {
                openScoresFragment()
            }
            R.id.menuLeague -> {
                openLeaguesFragment()
            }
        }
        return true
    }

    private fun openLeaguesFragment() {
        supportFragmentManager
            .beginTransaction().add(R.id.layoutParent, CountryFragment())
            .commit()
    }
}
