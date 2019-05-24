package com.sun.livescore.ui.live_event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sun.livescore.R
import com.sun.livescore.data.model.event.Event
import com.sun.livescore.databinding.ItemAwayTeamEventBinding
import com.sun.livescore.databinding.ItemHomeTeamEventBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.ui.base.BaseViewHolder
import com.sun.livescore.ui.live_event.LiveEventAdapter.EventViewHolder

class LiveEventAdapter(private val events: List<Event>) :
    BaseRecyclerAdapter<ViewDataBinding, Event, EventViewHolder>(events) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewDataBinding, Event> {
        return when (viewType) {
            HOME_TEAM -> {
                EventViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), getLayoutRes(viewType), parent, false
                    ) as ItemHomeTeamEventBinding
                )
            }
            else -> {
                EventViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), getLayoutRes(viewType), parent, false
                    ) as ItemAwayTeamEventBinding
                )
            }
        }
    }

    override fun getLayoutRes(viewType: Int): Int =
        when (viewType) {
            HOME_TEAM ->
                R.layout.item_home_team_event
            else ->
                R.layout.item_away_team_event
        }

    override fun getItemViewType(position: Int): Int {
        return when (events[position].home_away) {
            HOME -> HOME_TEAM
            else -> AWAY_TEAM
        }
    }

    inner class EventViewHolder(binding: ViewDataBinding) : BaseViewHolder<ViewDataBinding, Event>(binding) {
        override fun bindView(binding: ViewDataBinding, position: Int, data: Event) {
            binding.run {
                when (this) {
                    is ItemHomeTeamEventBinding -> {
                        event = data
                    }
                    is ItemAwayTeamEventBinding -> {
                        event = data
                    }
                    else -> {
                    }
                }
            }
        }
    }

    companion object {
        const val HOME = "h"
        const val HOME_TEAM = 0
        const val AWAY_TEAM = 1
    }
}
