package com.sun.livescore.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<ViewBinding : ViewDataBinding, T>(val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bindView(binding: ViewBinding, position: Int, score: T)
}
