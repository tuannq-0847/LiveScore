package com.sun.livescore.util

object Util {
    fun getScoreFromString(scores: String?): Array<String> {
        scores?.let {
            for (i in 0 until it.length) {
                if (it[i] in Constants.START_CHAR..Constants.END_CHAR) {
                    for (j in i + 1 until it.length) {
                        if (it[j] in Constants.START_CHAR..Constants.END_CHAR) {
                            return arrayOf(it[i].toString(), it[j].toString())
                        }
                    }
                }
            }
        }
        return arrayOf()
    }

    fun cleanUpTimeString(time: String): String {
        var finalTime = ""
        for (i in 0 until time.length - 3) finalTime += time[i]
        return finalTime
    }
}
