package com.sun.livescore.data.repository

import com.sun.livescore.data.model.event.EventResponse
import com.sun.livescore.data.remote.live.LiveEventDataSource
import com.sun.livescore.data.remote.live.LiveEventRemoteDataSource
import io.reactivex.Single

class LiveEventRepository(private val liveEventRemoteDataSource: LiveEventRemoteDataSource) :
    LiveEventDataSource.Remote {

    override fun getLiveEvents(id: String): Single<EventResponse> = liveEventRemoteDataSource.getLiveEvents(id)
}
