package com.sun.livescore.data.remote.live

import com.sun.livescore.data.model.event.EventResponse
import com.sun.livescore.data.remote.request.ApiService
import io.reactivex.Single

class LiveEventRemoteDataSource(private val apiService: ApiService) : LiveEventDataSource.Remote {
    override fun getLiveEvents(id: String): Single<EventResponse> = apiService.getEvents(id)
}
