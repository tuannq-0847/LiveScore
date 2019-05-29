package com.sun.livescore.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.livescore.R
import com.sun.livescore.data.model.team.Team
import com.sun.livescore.databinding.ItemFavoritesBinding
import com.sun.livescore.ui.base.BaseRecyclerAdapter
import com.sun.livescore.ui.base.BaseViewHolder
import com.sun.livescore.ui.favorite.FavoriteAdapter.FavViewHolder
import kotlinx.android.synthetic.main.item_favorites.view.imageNotification

class FavoriteAdapter(teams: List<Team>, private val favViewModel: FavoriteViewModel) :
    BaseRecyclerAdapter<ItemFavoritesBinding, Team, FavViewHolder>(teams) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemFavoritesBinding, Team> {
        return FavViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), getLayoutRes(viewType), parent, false
            )
        )
    }

    override fun getLayoutRes(viewType: Int) = R.layout.item_favorites

    inner class FavViewHolder(binding: ItemFavoritesBinding) : BaseViewHolder<ItemFavoritesBinding, Team>(binding) {
        override fun bindView(binding: ItemFavoritesBinding, position: Int, data: Team) {
            itemView.imageNotification.run {
                setOnClickListener {
                }
            }
            binding.team = data
            binding.viewModel = favViewModel
        }
    }
}
