package com.sun.livescore.ui.favorite

import android.view.View
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.livescore.R
import com.sun.livescore.data.model.EnumStatus.ERROR
import com.sun.livescore.data.model.EnumStatus.LOADING
import com.sun.livescore.data.model.EnumStatus.SUCCESS
import com.sun.livescore.data.model.team.Team
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.fragment_favorite.progressFavLoading
import kotlinx.android.synthetic.main.fragment_favorite.recyclerFavorites
import kotlinx.android.synthetic.main.fragment_favorite.searchViewFavorites
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment(), OnQueryTextListener {

    private val viewModel: FavoriteViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_favorite

    override fun initComponents() {
        viewModel.getFavorites()
        viewModel.favLiveData.observe(this, Observer {
            when (it.status) {
                SUCCESS -> showSuccess(it.data)
                ERROR -> showError(it.message)
                LOADING -> showLoading(true)
            }
        })
        val editText = searchViewFavorites.findViewById<AutoCompleteTextView>(R.id.search_src_text)
        context?.let {
            editText.setHintTextColor(ContextCompat.getColor(it, R.color.color_white))
            editText.setTextColor(ContextCompat.getColor(it, R.color.color_white))
            searchViewFavorites.setOnQueryTextListener(this)
        }
        observeFav()
    }

    private fun observeFav() {
        viewModel.dbLiveData.observe(this, Observer {
            when (it.status) {
                SUCCESS -> showInsertSuccess()
                ERROR -> showError(it.message)
                LOADING -> {
                }
            }
        })
    }

    private fun showInsertSuccess() {
        context?.showMessage(resources.getString(R.string.successfully))
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            viewModel.searchLeaguesByName(newText)
        }
        return true
    }

    private fun showLoading(isLoading: Boolean) {
        progressFavLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        context?.showMessage(message)
    }

    private fun showSuccess(data: List<Team>?) {
        showLoading(false)
        data?.run {
            val adapter = FavoriteAdapter(this, viewModel)
            recyclerFavorites.layoutManager = LinearLayoutManager(context)
            recyclerFavorites.adapter = adapter
        }
    }
}
