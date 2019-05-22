package com.sun.livescore.ui.live_event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sun.livescore.R
import com.sun.livescore.data.model.event.Event
import com.sun.livescore.databinding.ItemAwayTeamEventBinding
import com.sun.livescore.databinding.ItemHomeTeamEventBinding
import com.sun.livescore.ui.live_event.LiveEventAdapter.LiveBaseViewHolder
import com.sun.livescore.util.Util
import kotlinx.android.synthetic.main.item_away_team_event.view.imageEventAway
import kotlinx.android.synthetic.main.item_home_team_event.view.imageEvent

class LiveEventAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<LiveBaseViewHolder>() {

    override fun onBindViewHolder(holder: LiveBaseViewHolder, position: Int) {
        holder.onBind(events[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveBaseViewHolder {
        return when (viewType) {
            HOME_TEAM -> EventHomeViewHolder(
                ItemHomeTeamEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            AWAY_TEAM -> EventAwayViewHolder(
                ItemAwayTeamEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> EventHomeViewHolder(
                ItemHomeTeamEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun getItemCount(): Int = events.size

    override fun getItemViewType(position: Int): Int {
        return when (events[position].home_away) {
            HOME -> HOME_TEAM
            else -> AWAY_TEAM
        }
    }

    inner class EventHomeViewHolder(private val binding: ItemHomeTeamEventBinding) :
        LiveBaseViewHolder(binding) {

        override fun onBind(event: Event, position: Int) {
            binding.run {
                this.event = event
                handleHomeEventInMatch(event, itemView)
            }
        }
    }

    private fun handleHomeEventInMatch(event: Event, itemView: View) {
        when (event.event) {
            GOAL -> itemView.imageEvent.setBackgroundResource(R.drawable.ic_goal)
            RED_CARD -> itemView.imageEvent.setBackgroundResource(R.drawable.ic_red_card)
            YELLOW_RED_CARD -> itemView.imageEvent.setBackgroundResource(R.drawable.double_yellow)
            YELLOW_CARD -> itemView.imageEvent.setBackgroundResource(R.drawable.ic_yellow_card)
        }
    }

    private fun handleAwayEventInMatch(event: Event, itemView: View) {
        when (event.event) {
            GOAL -> itemView.imageEventAway.setBackgroundResource(R.drawable.ic_goal)
            RED_CARD -> itemView.imageEventAway.setBackgroundResource(R.drawable.ic_red_card)
            YELLOW_RED_CARD -> itemView.imageEventAway.setBackgroundResource(R.drawable.double_yellow)
            YELLOW_CARD -> itemView.imageEventAway.setBackgroundResource(R.drawable.ic_yellow_card)
        }
    }

    inner class EventAwayViewHolder(private val binding: ItemAwayTeamEventBinding) :
        LiveBaseViewHolder(binding) {

        override fun onBind(event: Event, position: Int) {
            binding.run {
                this.event = event
                handleAwayEventInMatch(event, itemView)
            }
        }
    }

    abstract class LiveBaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun onBind(event: Event, position: Int)
    }

    companion object {
        const val HOME = "h"
        const val GOAL = "GOAL"
        const val YELLOW_CARD = "YELLOW_CARD"
        const val RED_CARD = "RED_CARD"
        const val YELLOW_RED_CARD = "YELLOW_RED_CARD"
        const val HOME_TEAM = 0
        const val AWAY_TEAM = 1
    }
}
