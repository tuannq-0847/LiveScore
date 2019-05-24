package com.sun.livescore.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.sun.livescore.R
import com.sun.livescore.data.model.event.Event

@BindingAdapter("onHandleLiveEvent")
fun AppCompatImageView.handleLiveEvent(event: Event) {
    when (event.event) {
        GOAL -> setBackgroundResource(R.drawable.ic_goal)
        RED_CARD -> setBackgroundResource(R.drawable.ic_red_card)
        YELLOW_RED_CARD -> setBackgroundResource(R.drawable.double_yellow)
        YELLOW_CARD -> setBackgroundResource(R.drawable.ic_yellow_card)
    }
}

const val GOAL = "GOAL"
const val YELLOW_CARD = "YELLOW_CARD"
const val RED_CARD = "RED_CARD"
const val YELLOW_RED_CARD = "YELLOW_RED_CARD"
