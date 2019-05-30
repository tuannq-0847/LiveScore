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

class FavoriteAdapter(
    teams: List<Team>,
    private val saveFavoriteTeamOnClick: (teamId: String) -> Unit,
    private val removeFavoriteTeamOnClick: (teamId: String) -> Unit
) :
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
            checkStatusAndShowIcon(data.key)
            itemView.imageNotification.run {
                setOnClickListener {
                    when (data.key) {
                        null -> {
                            onSaveFavoriteTeam(data.teamId)
                            data.key = FOLLOWED
                        }
                        else -> {
                            onRemoveFavoriteTeam(data.teamId)
                            data.key = null
                        }
                    }
                    notifyItemChanged(position)
                }
            }
            binding.team = data
        }

        private fun checkStatusAndShowIcon(key: String?) {
            when (key) {
                FOLLOWED -> {
                    showIconFollow()
                }
                else -> {
                    showIconUnFollow()
                }
            }
        }

        private fun onSaveFavoriteTeam(teamId: String) {
            saveFavoriteTeamOnClick(teamId)
            showIconFollow()
        }

        private fun onRemoveFavoriteTeam(teamId: String) {
            removeFavoriteTeamOnClick(teamId)
            showIconUnFollow()
        }

        private fun showIconUnFollow() {
            itemView.imageNotification?.setImageResource(R.drawable.ic_notifications_black_24dp)
        }

        private fun showIconFollow() {
            itemView.imageNotification?.setImageResource(R.drawable.ic_notifications_follow)
        }
    }

    companion object {
        const val FOLLOWED = "1"
    }
}
