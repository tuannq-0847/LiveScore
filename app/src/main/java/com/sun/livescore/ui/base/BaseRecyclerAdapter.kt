package com.sun.livescore.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.ui.base.BaseRecyclerAdapter.BaseViewHolder

abstract class BaseRecyclerAdapter<ViewBinding : ViewDataBinding, T>(private val scores: List<T>) :
    RecyclerView.Adapter<BaseViewHolder<ViewBinding>>() {

    override fun getItemCount(): Int {
        return scores.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding> {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getLayoutRes(viewType),
                parent, false
            )
        )
    }

    protected abstract fun getLayoutRes(viewType: Int): Int

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        bindView(holder.binding, position, scores[position])
    }

    protected open fun bindView(binding: ViewBinding, position: Int, score: T) {
    }

    open class BaseViewHolder<ViewBinding : ViewDataBinding>(val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
