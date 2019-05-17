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

abstract class BaseRecyclerAdapter<ViewBinding : ViewDataBinding>(private val baseScore: Any) :
    RecyclerView.Adapter<BaseViewHolder<ViewBinding>>() {

    override fun getItemCount(): Int {
        return when (baseScore) {
            is HistoryResponse
            -> baseScore.data?.histories?.size?.let {
                it
            }!!
            is FixtureResponse
            -> baseScore.data?.fixtures?.size?.let {
                it
            }!!
            else -> 0
        }
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
        when (baseScore) {
            is HistoryResponse
            -> baseScore.data?.histories?.let {
                bindViewHistory(holder.binding, it, position)
            }
            is FixtureResponse
            -> baseScore.data?.fixtures?.let {
                bindViewFixture(holder.binding, it, position)
            }

        }
    }

    protected open fun bindViewFixture(binding: ViewBinding, fixtures: List<Fixture>, position: Int) {
    }

    protected open fun bindViewHistory(binding: ViewBinding, histories: List<History>, position: Int) {
    }

    open class BaseViewHolder<ViewBinding : ViewDataBinding>(val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
