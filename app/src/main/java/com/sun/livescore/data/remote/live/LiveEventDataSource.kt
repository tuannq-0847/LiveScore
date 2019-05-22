package com.sun.livescore.data.remote.live

import com.sun.livescore.data.model.event.EventResponse
import io.reactivex.Single

interface LiveEventDataSource {
    interface Remote {
        fun getLiveEvents(id: String): Single<EventResponse>
    }
}
