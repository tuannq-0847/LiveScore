package com.sun.livescore.ui.favorite

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.livescore.R
import com.sun.livescore.data.model.EnumStatus.ERROR
import com.sun.livescore.data.model.EnumStatus.LOADING
import com.sun.livescore.data.model.EnumStatus.SUCCESS
import com.sun.livescore.data.model.team.Team
import com.sun.livescore.service.ScheduleService
import com.sun.livescore.ui.base.BaseFragment
import com.sun.livescore.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.fragment_favorite.progressFavLoading
import kotlinx.android.synthetic.main.fragment_favorite.recyclerFavorites
import kotlinx.android.synthetic.main.fragment_favorite.searchViewFavorites
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class FavoriteFragment : BaseFragment(), OnQueryTextListener {

    private val viewModel: FavoriteViewModel by viewModel()
    override val layoutId = R.layout.fragment_favorite

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
        observeSchedule()
    }

    private fun observeSchedule() {
        viewModel.scheduleLiveData.observe(this, Observer {
            when (it.status) {
                SUCCESS -> showSaveScheduleSuccess(it.data)
                ERROR -> showError(it.message)
                LOADING -> {
                }
            }
        })
    }

    private fun showSaveScheduleSuccess(data: List<Int>?) {
        context?.startService(ScheduleService.getServiceIntent(context, data))
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
            viewModel.searchTeamsByName(newText)
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
            val adapter = FavoriteAdapter(this, { teamId -> saveFavoriteTeamOnClick(teamId) },
                { teamId -> removeFavoriteTeamOnClick(teamId) })
            recyclerFavorites.layoutManager = LinearLayoutManager(context)
            recyclerFavorites.adapter = adapter
        }
    }

    private fun saveFavoriteTeamOnClick(teamId: String) {
        viewModel.saveFavoriteTeam(teamId)
        showDateTimePicker(teamId)
    }

    private fun showDateTimePicker(teamId: String) {
        val calendar = Calendar.getInstance()
        val datePickerListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, day: Int ->
                showTimePicker(year, month, day, teamId)
            }
        val datePickerDialog = DatePickerDialog(
            context, datePickerListener, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - DEFAULT_VALUE
        datePickerDialog.show()
    }

    private fun showTimePicker(year: Int, month: Int, day: Int, teamId: String) {
        val calendar = Calendar.getInstance()
        val timePickerListener =
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                getDateTime("$year-$month-$day", "$hourOfDay-$minute", teamId)
            }
        val timePickerDialog = TimePickerDialog(
            context, timePickerListener, calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), false
        )
        timePickerDialog.show()
    }

    private fun removeFavoriteTeamOnClick(teamId: String) {
        viewModel.removeFavoriteTeam(teamId)
        viewModel.removeSchedule(teamId)
    }

    private fun getDateTime(date: String, time: String, teamId: String) {
        viewModel.saveSchedule(date, time, teamId)
    }

    companion object {
        const val DEFAULT_VALUE = 1000
    }
}
