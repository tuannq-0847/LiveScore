package com.sun.livescore.ui.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<ViewBinding : ViewDataBinding, T, VH : BaseViewHolder<ViewBinding, T>>(private val scores: List<T>) :
    RecyclerView.Adapter<BaseViewHolder<ViewBinding, T>>() {

    override fun getItemCount(): Int {
        return scores.size
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding, T>

    protected abstract fun getLayoutRes(viewType: Int): Int

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, T>, position: Int) {
        holder.bindView(holder.binding, position, scores[position])
    }
}
